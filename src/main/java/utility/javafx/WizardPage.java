package utility.javafx;

import java.util.function.Supplier;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public abstract class WizardPage<T> extends DialogPane implements Initializable
{        
    protected ObjectProperty<Wizard<T>> wizardProperty = new SimpleObjectProperty<> ();
    protected ObjectProperty<T> productProperty = new SimpleObjectProperty<> ();
    
    // These properties define predicates to determine which movement actions are possible.
    
    protected BooleanProperty canMoveForwardProperty = new SimpleBooleanProperty ( true );
    protected BooleanProperty canMoveBackwardProperty = new SimpleBooleanProperty ( true );
    protected BooleanProperty canFinishProperty = new SimpleBooleanProperty ( true );
        
    // These properties define actions to take upon movement.
    
    protected ObjectProperty<Runnable> onEnterForwardProperty  = new SimpleObjectProperty<> ( null );
    protected ObjectProperty<Runnable> onExitForwardProperty   = new SimpleObjectProperty<> ( null );
    protected ObjectProperty<Runnable> onEnterBackwardProperty = new SimpleObjectProperty<> ( null );
    protected ObjectProperty<Runnable> onExitBackwardProperty  = new SimpleObjectProperty<> ( null );
    
    // This property defines the previous page to return to.
    
    protected ObjectProperty<WizardPage<T>> previousPageProperty = new SimpleObjectProperty<> ();
    
    // These properties define the next page to go to.
    
    protected ObjectProperty<WizardPage<T>> nextPageProperty = new SimpleObjectProperty<> ();
    protected ObjectProperty<Supplier<WizardPage<T>>> nextPageSupplierProperty = new SimpleObjectProperty<> ();
    
    public WizardPage ()
    {
        getButtonTypes ().addListener ( ( ListChangeListener.Change<? extends ButtonType> change ) -> 
        {
            while ( change.next () )
                if ( change.wasAdded () )
                    for ( ButtonType buttonType : change.getAddedSubList () )
                    {
                        Button button = ( Button ) WizardPage.this.lookupButton ( buttonType );

                        if ( buttonType == ButtonType.PREVIOUS )
                        {
                            button.addEventFilter ( ActionEvent.ACTION, event -> 
                            {
                                event.consume ();
                                WizardPage.this.exitBackward();
                            } );
                            
                            button.disableProperty ().bind ( canMoveBackwardProperty.not ().or ( previousPageProperty.isNull () ) );
                        }

                        if ( buttonType == ButtonType.NEXT )
                        {
                            button.addEventFilter ( ActionEvent.ACTION, event -> 
                            {
                                event.consume ();
                                WizardPage.this.exitForward();
                            } );
                            
                            button.disableProperty ().bind ( canMoveForwardProperty.not ().or ( nextPageSupplierProperty.isNull ().and ( nextPageProperty.isNull () ) ) );
                        }

                        if ( buttonType == ButtonType.FINISH )
                        {
                            button.addEventFilter ( ActionEvent.ACTION, event -> WizardPage.this.finish() );
                            button.disableProperty ().bind ( canFinishProperty.not () );                        
                        }
                    }
        } );
    }
    
    public WizardPage<T> follows ( WizardPage<T> other )
    {
        other.nextPageProperty.set ( this );
        
        return this;
    }
    
    void enterForward ( WizardPage<T> previous )
    {
        previousPageProperty.set ( previous );

        if ( onEnterForwardProperty.get () != null )
            onEnterForwardProperty.get ().run ();
        
        wizardProperty.get ().showPage ( this );
    }
    
    void enterBackward()
    {
        if ( onEnterBackwardProperty.get () != null )
            onEnterBackwardProperty.get ().run ();   
        
        wizardProperty.get ().showPage ( this );
    }
    
    void exitForward()
    {
        if ( nextPageSupplierProperty.get () != null )
            exitForward ( nextPageSupplierProperty.get ().get () );
        
        if ( nextPageProperty.get () != null )
            exitForward ( nextPageProperty.get () );
    }
    
    void exitForward ( WizardPage<T> next )
    {
        if ( onExitForwardProperty.get () != null )
            onExitForwardProperty.get ().run ();
        
        next.enterForward ( this );
    }
    
    void exitBackward()
    {
        if ( onExitBackwardProperty.get () != null )
            onExitBackwardProperty.get ().run ();
        
        previousPageProperty.get ().enterBackward ();
    }

    void finish()
    {
        if ( onExitForwardProperty.get () != null )
            onExitForwardProperty.get ().run ();
        
        wizardProperty.get ().finish ();
    }

    public ObjectProperty<Wizard<T>> wizardProperty ()
    {
        return wizardProperty;
    }

    public BooleanProperty canMoveForwardProperty ()
    {
        return canMoveForwardProperty;
    }

    public BooleanProperty canMoveBackwardProperty ()
    {
        return canMoveBackwardProperty;
    }

    public BooleanProperty canFinishProperty ()
    {
        return canFinishProperty;
    }

    public ObjectProperty<Runnable> onEnterForwardProperty ()
    {
        return onEnterForwardProperty;
    }

    public ObjectProperty<Runnable> onExitForwardProperty ()
    {
        return onExitForwardProperty;
    }

    public ObjectProperty<Runnable> onEnterBackwardProperty ()
    {
        return onEnterBackwardProperty;
    }

    public ObjectProperty<Runnable> onExitBackwardProperty ()
    {
        return onExitBackwardProperty;
    }

    public ObjectProperty<WizardPage<T>> previousPageProperty ()
    {
        return previousPageProperty;
    }

    public ObjectProperty<WizardPage<T>> nextPageProperty ()
    {
        return nextPageProperty;
    }

    public ObjectProperty<Supplier<WizardPage<T>>> nextPageSupplierProperty ()
    {
        return nextPageSupplierProperty;
    }
}

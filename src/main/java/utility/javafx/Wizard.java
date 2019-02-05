package utility.javafx;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.SetChangeListener;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class Wizard<T> extends Dialog<ButtonType>
{
    ObjectProperty<T> productProperty = new SimpleObjectProperty<> ();
    protected SetProperty<WizardPage<T>> pagesProperty = new SimpleSetProperty<> ( FXCollections.observableSet ( new HashSet<>() ) );
    protected ObjectProperty<WizardPage<T>> firstPageProperty = new SimpleObjectProperty<> ();
    protected ObjectProperty<Runnable> onFinishProperty = new SimpleObjectProperty<> ();
    
    public Wizard ()
    {
        pagesProperty.addListener ( ( SetChangeListener.Change<? extends WizardPage<T>> change ) -> 
        {
            if ( change.wasRemoved () )
            {
                change.getElementRemoved ().wizardProperty.set ( null );
                change.getElementRemoved ().productProperty.unbind ();
            }
            
            if ( change.wasAdded () )
            {
                change.getElementAdded ().wizardProperty.set ( this );
                change.getElementAdded ().productProperty.bind ( productProperty );
            }
        } );
    }
    
    public T product()
    {
        return productProperty.get ();
    }
    
    public Wizard<T> model ( T model )
    {
        productProperty.set ( model );
        return this;
    }
    
    void showPage ( WizardPage<T> page )
    {
        setDialogPane ( page );
    }
    
    void finish()
    {
        if ( onFinishProperty.get () != null )
            onFinishProperty.get ().run ();
    }
    
    public Wizard<T> addPage ( WizardPage<T> page )
    {
        pagesProperty.add ( page );
        
        if ( pagesProperty.size () == 1 )
            firstPageProperty.set ( page );
        
        return this;
    }
    
    @SafeVarargs
    public final void simpleSequence ( WizardPage<T>... pages )
    {
        simpleSequence ( Arrays.asList ( pages ) );
    }   
    
    public void simpleSequence ( List<WizardPage<T>> pages )
    {
        if ( pages.size () == 0 )
            throw new IllegalArgumentException ( "Must supply at least one Page" );
        
        for ( int n=0; n<pages.size (); n++ )
        {
            WizardPage<T> page = pages.get ( n );
            addPage ( page );
                        
            if ( n > 0 )
                page.follows ( pages.get ( n-1 ) );
        }
    }
    
    public Optional<ButtonType> cast()
    {
        firstPageProperty.get ().enterForward ( null );
        return showAndWait ();
    }

    public ObjectProperty<T> productProperty ()
    {
        return productProperty;
    }

    public SetProperty<WizardPage<T>> pagesProperty ()
    {
        return pagesProperty;
    }

    public ObjectProperty<WizardPage<T>> firstPageProperty ()
    {
        return firstPageProperty;
    }
}

package utility.javafx;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WizardTest extends Application
{
    public static void main ( String[] args )
    {
        launch ( args );
    }
    
    @Override
    public void start ( Stage stage ) throws Exception
    {
        Button testButton = new Button();
        testButton.setText ( "Test" );
        testButton.setOnAction ( event -> 
        {
            Wizard<StringBuilder> wizard = setup();
            wizard.model ( new StringBuilder() );
            
            Optional<ButtonType> result = wizard.cast ();
            
            if ( result.isPresent () )
            {
                System.out.println ( result.get () );
                
                if ( result.get () == ButtonType.FINISH )
                    System.out.println ( "Built: " + wizard.product ().toString () );
            }
            else
                System.out.println ( "No Result" );
        } );
        
        Scene scene = new Scene ( testButton );
        stage.setScene ( scene );
        stage.setTitle ( "Wizard Test" );
        stage.show ();
    }
    
    protected Wizard<StringBuilder> setup()
    {
        Wizard<StringBuilder> wizard = new Wizard<>();
        wizard.setTitle ( "Test Wizard" );
        
        //
        // Appetizer Page
        //
        
        WizardPage<StringBuilder> appetizer = new WizardPage<StringBuilder>()
        {
            @Override
            public void initialize ( URL location, ResourceBundle resources )
            {
                setHeaderText ( "Select an Appetizer" );
                getButtonTypes ().addAll ( ButtonType.NEXT, ButtonType.CANCEL );
                Label label = new Label();
                label.setText ( "You are getting Spinach Puffs, please move forward" );
                setContent ( label );
                
                onExitForwardProperty.set ( () -> wizardProperty.get ().product ().append ( "Spinach Puffs" ) );
                onEnterBackwardProperty.set ( () -> wizardProperty.get ().product ().setLength ( 0 ) );
            }
        };
        
        wizard.addPage ( appetizer );
        
        //
        // Soup Page
        //
        
        WizardPage<StringBuilder> soup = new WizardPage<StringBuilder> () 
        {
            @Override
            public void initialize ( URL location, ResourceBundle resources )
            {
                setHeaderText ( "Select a Soup" );
                getButtonTypes ().addAll ( ButtonType.PREVIOUS, ButtonType.NEXT, ButtonType.CANCEL, ButtonType.FINISH );
                
                Button nextButton = ( Button ) lookupButton ( ButtonType.NEXT );
                nextButton.setText ( "Salad" );
                nextButton.setPrefWidth ( USE_COMPUTED_SIZE );
                
                CheckBox wantSoup = new CheckBox();
                wantSoup.setText ( "I would like Soup" );
                wantSoup.addEventFilter ( ActionEvent.ACTION, event -> 
                {
                    StringBuilder model = wizardProperty.get ().product ();
                    
                    if ( wantSoup.isSelected () )
                        model.append ( ", Soup" );
                    else
                        model.setLength ( model.length () - 6 );
                } );
                
                canFinishProperty.bind ( wantSoup.selectedProperty () );
                
                setContent ( wantSoup );
            }
        };
        
        soup.follows ( appetizer );
        wizard.addPage ( soup );
        
        //
        
        return wizard;
    }
}

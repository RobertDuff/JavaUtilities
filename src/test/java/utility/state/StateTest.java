package utility.state;

import static org.easymock.EasyMock.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StateTest
{
	interface FuncClass extends Function<Event,List<Event>>{}
	interface PredClass extends Predicate<Event>{}

    public static final List<Event> nullEvents = Collections.emptyList();
    
    // Mock Objects
    
    public static FuncClass aEnter;
    public static FuncClass aExit;
    public static FuncClass bEnter;
    public static FuncClass bExit;
    public static FuncClass cEnter;
    public static FuncClass cExit;
    public static FuncClass xEnter;
    public static FuncClass xExit;
    public static FuncClass yEnter;
    public static FuncClass yExit;
    public static FuncClass pEnter;
    public static FuncClass pExit;
    public static FuncClass qEnter;
    public static FuncClass qExit;

    public static PredClass abGuard;
    public static FuncClass abAction;

    public static PredClass bcGuard;
    public static FuncClass bcAction;
    
    public static PredClass caGuard;
    public static FuncClass caAction;
    
    public static PredClass bbGuard;
    public static FuncClass bbAction;
    
    public static PredClass ccGuard;
    public static FuncClass ccAction;
    
    public static PredClass abcGuard;
    public static FuncClass abcAction;
    
    public static PredClass adGuard;
    public static FuncClass adAction;

    public static PredClass dPredicate;
    
    public static FuncClass dbAction;
    public static FuncClass dcAction;
    
    public static PredClass xyGuard;
    public static FuncClass xyAction;
    
    public static PredClass yxGuard;
    public static FuncClass yxAction;
    
    public static PredClass pqGuard;
    public static FuncClass pqAction;
    
    public static PredClass qpGuard;
    public static FuncClass qpAction;
    
    // ----------------------------------------
    // State Model Definition
    // ----------------------------------------
    
    // Events
    
    public static Event toA;
    public static Event toB;
    public static Event toC;
    public static Event toBC;
    public static Event toD;

    public static Event toX;
    public static Event toY;

    public static Event toP;
    public static Event toQ;

    // States
    
    public static State sA;
    public static State sB;
    public static State sC;
    public static State sD;

    public static State sX;
    public static State sY;

    public static State sP;
    public static State sQ;

    // Models
    
    public static StateModel pqModel;
    public static StateModel xyModel;
    public static StateModel abcModel;
    
    // Machine
    
    public static StateMachine fsm;

    @BeforeAll
    public static void beforeAll()
    {        
        setupMocks();
        createABCModel();
        
        fsm = new StateMachine ( abcModel );
    }

    private static void createABCModel()
    {
        toA = new Event ( "-a" );
        toB = new Event ( "-b" );
        toC = new Event ( "-c" );
        
        toBC = new Event ( "-b-c" );
        toD = new Event ( "-d" );
        
        createXYModel();
        createPQModel();

        sA = new State ( "A", aEnter, aExit );
        sB = new State ( "B", bEnter, bExit );
        sC = new State ( "C", cEnter, cExit, xyModel, pqModel );
        
        Reaction db = new Reaction ( dbAction, sB );
        Reaction dc = new Reaction ( dcAction, sC );
        
        sD = new Choice ( "D", dPredicate, db, dc );
        
        sA.reactions().put ( toB, new Reaction ( abGuard, abAction, sB ) );
        sA.reactions().put ( toBC, new Reaction ( abcGuard, abcAction, sB ) );
        sA.reactions().put ( toD, new Reaction ( adGuard, adAction, sD ) );
        
        sB.reactions().put ( toB, new Reaction ( bbGuard, bbAction ) );
        sB.reactions().put ( toC, new Reaction ( bcGuard, bcAction, sC ) );
        
        sC.reactions().put ( toA, new Reaction ( caGuard, caAction, sA ) );
        sC.reactions().put ( toC, new Reaction ( ccGuard, ccAction, sC ) );
        
        abcModel = new StateModel ( sA );
    }

    private static void createPQModel()
    {
        toP = new Event ( "q-p" );
        toQ = new Event ( "p-q" );
        
        sP = new State ( "P", pEnter, pExit );
        sQ = new State ( "Q", qEnter, qExit );
        
        sP.reactions().put ( toQ, new Reaction ( pqGuard, pqAction, sQ ) );
        sQ.reactions().put ( toP, new Reaction ( qpGuard, qpAction, sP ) );
        
        pqModel = new StateModel ( sP, true );
    }
    
    private static void createXYModel()
    {
        toX = new Event ( "y-x" );
        toY = new Event ( "x-y" );
        
        sX = new State ( "X", xEnter, xExit );
        sY = new State ( "Y", yEnter, yExit );
        
        sX.reactions().put ( toY, new Reaction ( xyGuard, xyAction, sY ) );
        sY.reactions().put ( toX, new Reaction ( yxGuard, yxAction, sX ) );
        
        xyModel = new StateModel ( sX );
    }
    
    private static void setupMocks()
    {    	
        aEnter = createMock ( FuncClass.class );
        aExit  = createMock ( FuncClass.class );
        bEnter = createMock ( FuncClass.class );
        bExit  = createMock ( FuncClass.class );
        cEnter = createMock ( FuncClass.class );
        cExit  = createMock ( FuncClass.class );
        xEnter = createMock ( FuncClass.class );
        xExit  = createMock ( FuncClass.class );
        yEnter = createMock ( FuncClass.class );
        yExit  = createMock ( FuncClass.class );
        pEnter = createMock ( FuncClass.class );
        pExit  = createMock ( FuncClass.class );
        qEnter = createMock ( FuncClass.class );
        qExit  = createMock ( FuncClass.class );
        
        abGuard    = createMock ( PredClass.class );
        abAction   = createMock ( FuncClass.class );
        bcGuard    = createMock ( PredClass.class );
        bcAction   = createMock ( FuncClass.class );
        caGuard    = createMock ( PredClass.class );
        caAction   = createMock ( FuncClass.class );
        bbGuard    = createMock ( PredClass.class );
        bbAction   = createMock ( FuncClass.class );
        ccGuard    = createMock ( PredClass.class );
        ccAction   = createMock ( FuncClass.class );
        abcGuard   = createMock ( PredClass.class );
        abcAction  = createMock ( FuncClass.class );
        adGuard    = createMock ( PredClass.class );
        adAction   = createMock ( FuncClass.class );
        dPredicate = createMock ( PredClass.class );
        dbAction   = createMock ( FuncClass.class );
        dcAction   = createMock ( FuncClass.class );
        xyGuard    = createMock ( PredClass.class );
        xyAction   = createMock ( FuncClass.class );
        yxGuard    = createMock ( PredClass.class );
        yxAction   = createMock ( FuncClass.class );
        pqGuard    = createMock ( PredClass.class );
        pqAction   = createMock ( FuncClass.class );
        qpGuard    = createMock ( PredClass.class );
        qpAction   = createMock ( FuncClass.class );
    }
    
    private void resetMocks()
    {
        reset ( aEnter );
        reset ( aExit );
        reset ( bEnter );
        reset ( bExit );
        reset ( cEnter );
        reset ( cExit );
        reset ( xEnter );
        reset ( xExit );
        reset ( yEnter );
        reset ( yExit );
        reset ( pEnter );
        reset ( pExit );
        reset ( qEnter );
        reset ( qExit );
        
        reset ( abGuard );
        reset ( abAction );
        reset ( bcGuard );
        reset ( bcAction );
        reset ( caGuard );
        reset ( caAction );
        reset ( bbGuard );
        reset ( bbAction );
        reset ( ccGuard );
        reset ( ccAction );
        reset ( abcGuard );
        reset ( abcAction );
        reset ( adGuard );
        reset ( adAction );
        reset ( dPredicate );
        reset ( dbAction );
        reset ( dcAction );
        reset ( xyGuard );
        reset ( xyAction );
        reset ( yxGuard );
        reset ( yxAction );
        reset ( pqGuard );
        reset ( pqAction );
        reset ( qpGuard );
        reset ( qpAction );

    }
    
    private void replayMocks()
    {
        replay ( aEnter );
        replay ( aExit );
        replay ( bEnter );
        replay ( bExit );
        replay ( cEnter );
        replay ( cExit );
        replay ( xEnter );
        replay ( xExit );
        replay ( yEnter );
        replay ( yExit );
        replay ( pEnter );
        replay ( pExit );
        replay ( qEnter );
        replay ( qExit );

        replay ( abGuard );
        replay ( abAction );
        replay ( bcGuard );
        replay ( bcAction );
        replay ( caGuard );
        replay ( caAction );
        replay ( bbGuard );
        replay ( bbAction );
        replay ( ccGuard );
        replay ( ccAction );
        replay ( abcGuard );
        replay ( abcAction );
        replay ( adGuard );
        replay ( adAction );
        replay ( dPredicate );
        replay ( dbAction );
        replay ( dcAction );
        replay ( xyGuard );
        replay ( xyAction );
        replay ( yxGuard );
        replay ( yxAction );
        replay ( pqGuard );
        replay ( pqAction );
        replay ( qpGuard );
        replay ( qpAction );
    }
    
    private void verifyMocks()
    {
        verify ( aEnter );
        verify ( aExit );
        verify ( bEnter );
        verify ( bExit );
        verify ( cEnter );
        verify ( cExit );
        verify ( xEnter );
        verify ( xExit );
        verify ( yEnter );
        verify ( yExit );
        verify ( pEnter );
        verify ( pExit );
        verify ( qEnter );
        verify ( qExit );
        
        verify ( abGuard );
        verify ( abAction );
        verify ( bcGuard );
        verify ( bcAction );
        verify ( caGuard );
        verify ( caAction );
        verify ( bbGuard );
        verify ( bbAction );
        verify ( ccGuard );
        verify ( ccAction );
        verify ( abcGuard );
        verify ( abcAction );
        verify ( adGuard );
        verify ( adAction );
        verify ( dPredicate );
        verify ( dbAction );
        verify ( dcAction );
        verify ( xyGuard );
        verify ( xyAction );
        verify ( yxGuard );
        verify ( yxAction );
        verify ( pqGuard );
        verify ( pqAction );
        verify ( qpGuard );
        verify ( qpAction );
    }
    
    @Test
    public void testLoop()
    {
        resetMocks();
        
        expect ( aEnter.apply ( Event.INITIALIZE_EVENT ) ).andReturn ( nullEvents );
        
        expect ( abGuard.test ( toB ) ).andReturn ( true );
        expect ( aExit.apply ( toB ) ).andReturn ( nullEvents );
        expect ( abAction.apply ( toB ) ).andReturn ( nullEvents );
        expect ( bEnter.apply ( toB ) ).andReturn ( nullEvents );
        
        expect ( bcGuard.test ( toC ) ).andReturn ( true );
        expect ( bExit.apply ( toC ) ).andReturn ( nullEvents );
        expect ( bcAction.apply ( toC ) ).andReturn ( nullEvents );
        expect ( cEnter.apply ( toC ) ).andReturn ( nullEvents );
        expect ( xEnter.apply ( toC ) ).andReturn ( nullEvents );
        expect ( pEnter.apply ( toC ) ).andReturn ( nullEvents );
        
        expect ( xyGuard.test ( toY ) ).andReturn ( true );
        expect ( xExit.apply ( toY ) ).andReturn ( null );
        expect ( xyAction.apply ( toY ) ).andReturn ( null );
        expect ( yEnter.apply ( toY ) ).andReturn ( null );
        
        expect ( pqGuard.test ( toQ ) ).andReturn ( true );
        expect ( pExit.apply ( toQ ) ).andReturn ( null );
        expect ( pqAction.apply ( toQ ) ).andReturn ( null );
        expect ( qEnter.apply ( toQ ) ).andReturn ( null );
        
        expect ( yxGuard.test ( toX ) ).andReturn ( true );
        expect ( yExit.apply ( toX ) ).andReturn ( null );
        expect ( yxAction.apply ( toX ) ).andReturn ( null );
        expect ( xEnter.apply ( toX ) ).andReturn ( null );
        
        expect ( qpGuard.test ( toP ) ).andReturn ( true );
        expect ( qExit.apply ( toP ) ).andReturn ( null );
        expect ( qpAction.apply ( toP ) ).andReturn ( null );
        expect ( pEnter.apply ( toP ) ).andReturn ( null );
        
        expect ( caGuard.test ( toA ) ).andReturn ( true );
        expect ( cExit.apply ( toA ) ).andReturn ( nullEvents );
        expect ( xExit.apply ( toA ) ).andReturn ( nullEvents );
        expect ( pExit.apply ( toA ) ).andReturn ( nullEvents );
        expect ( caAction.apply ( toA ) ).andReturn ( nullEvents );
        expect ( aEnter.apply ( toA ) ).andReturn ( nullEvents );
        
        replayMocks();
        
        fsm.init();
        Assertions.assertEquals ( sA, fsm.currentState() );
        Assertions.assertEquals ( "A", fsm.currentState().toString() );

        fsm.react ( toB );
        Assertions.assertEquals ( sB, fsm.currentState() );
        Assertions.assertEquals ( "B", fsm.currentState().toString() );

        fsm.react ( toC );
        Assertions.assertEquals ( sC, fsm.currentState() );
        Assertions.assertEquals ( sX, fsm.currentState().subModels().get( 0 ).currentState() );
        Assertions.assertEquals ( sP, fsm.currentState().subModels().get( 1 ).currentState() );
        Assertions.assertEquals ( "C([X, P])", fsm.currentState().toString() );

        fsm.react ( toY );
        Assertions.assertEquals ( sC, fsm.currentState() );
        Assertions.assertEquals ( sY, fsm.currentState().subModels().get( 0 ).currentState() );
        Assertions.assertEquals ( sP, fsm.currentState().subModels().get( 1 ).currentState() );
        Assertions.assertEquals ( "C([Y, P])", fsm.currentState().toString() );

        fsm.react ( toQ );
        Assertions.assertEquals ( sC, fsm.currentState() );
        Assertions.assertEquals ( sY, fsm.currentState().subModels().get( 0 ).currentState() );
        Assertions.assertEquals ( sQ, fsm.currentState().subModels().get( 1 ).currentState() );
        Assertions.assertEquals ( "C([Y, Q])", fsm.currentState().toString() );

        fsm.react ( toX );
        Assertions.assertEquals ( sC, fsm.currentState() );
        Assertions.assertEquals ( sX, fsm.currentState().subModels().get( 0 ).currentState() );
        Assertions.assertEquals ( sQ, fsm.currentState().subModels().get( 1 ).currentState() );
        Assertions.assertEquals ( "C([X, Q])", fsm.currentState().toString() );

        fsm.react ( toP );
        Assertions.assertEquals ( sC, fsm.currentState() );
        Assertions.assertEquals ( sX, fsm.currentState().subModels().get( 0 ).currentState() );
        Assertions.assertEquals ( sP, fsm.currentState().subModels().get( 1 ).currentState() );
        Assertions.assertEquals ( "C([X, P])", fsm.currentState().toString() );

        fsm.react ( toA );
        Assertions.assertEquals ( sA, fsm.currentState() );
        Assertions.assertEquals ( "A", fsm.currentState().toString() );

        verifyMocks();
    }

    @Test
    public void testNoReaction()
    {
        resetMocks();
        
        expect ( aEnter.apply ( Event.INITIALIZE_EVENT ) ).andReturn ( nullEvents );
        
        replayMocks();
        
        fsm.init();
        Assertions.assertEquals ( sA, fsm.currentState() );
        
        fsm.react ( toA );
        Assertions.assertEquals ( sA, fsm.currentState() );

        verifyMocks();
    }

    @Test
    public void testInternalReaction()
    {
        resetMocks();
        
        expect ( aEnter.apply ( Event.INITIALIZE_EVENT ) ).andReturn ( nullEvents );
        
        expect ( abGuard.test ( toB ) ).andReturn ( true );
        expect ( aExit.apply ( toB ) ).andReturn ( nullEvents );
        expect ( abAction.apply ( toB ) ).andReturn ( nullEvents );
        expect ( bEnter.apply ( toB ) ).andReturn ( nullEvents );
        
        expect ( bbGuard.test ( toB ) ).andReturn ( true );
        expect ( bbAction.apply ( toB ) ).andReturn ( nullEvents );
        
        replayMocks();
        
        fsm.init();
        Assertions.assertEquals ( sA, fsm.currentState() );

        fsm.react ( toB );
        Assertions.assertEquals ( sB, fsm.currentState() );

        fsm.react ( toB );
        Assertions.assertEquals ( sB, fsm.currentState() );

        verifyMocks();
    }

    @Test
    public void testTransitionToSelf()
    {
        resetMocks();
        
        expect ( aEnter.apply ( Event.INITIALIZE_EVENT ) ).andReturn ( nullEvents );
        
        expect ( abGuard.test ( toB ) ).andReturn ( true );
        expect ( aExit.apply ( toB ) ).andReturn ( nullEvents );
        expect ( abAction.apply ( toB ) ).andReturn ( nullEvents );
        expect ( bEnter.apply ( toB ) ).andReturn ( nullEvents );
        
        expect ( bcGuard.test ( toC ) ).andReturn ( true );
        expect ( bExit.apply ( toC ) ).andReturn ( nullEvents );
        expect ( bcAction.apply ( toC ) ).andReturn ( nullEvents );
        expect ( cEnter.apply ( toC ) ).andReturn ( nullEvents );
        expect ( xEnter.apply ( toC ) ).andReturn ( nullEvents );
        expect ( pEnter.apply ( toC ) ).andReturn ( nullEvents );
        
        expect ( ccGuard.test ( toC ) ).andReturn ( true );
        expect ( cExit.apply ( toC ) ).andReturn ( nullEvents );
        expect ( xExit.apply ( toC ) ).andReturn ( nullEvents );
        expect ( pExit.apply ( toC ) ).andReturn ( nullEvents );
        expect ( ccAction.apply ( toC ) ).andReturn ( nullEvents );
        expect ( cEnter.apply ( toC ) ).andReturn ( nullEvents );
        expect ( xEnter.apply ( toC ) ).andReturn ( nullEvents );
        expect ( pEnter.apply ( toC ) ).andReturn ( nullEvents );
        
        replayMocks();
        
        fsm.init();
        Assertions.assertEquals ( sA, fsm.currentState() );

        fsm.react ( toB );
        Assertions.assertEquals ( sB, fsm.currentState() );

        fsm.react ( toC );
        Assertions.assertEquals ( sC, fsm.currentState() );

        fsm.react ( toC );
        Assertions.assertEquals ( sC, fsm.currentState() );
        
        verifyMocks();
    }

    @Test
    public void testDoubleEvent()
    {
        resetMocks();
        
        expect ( aEnter.apply ( Event.INITIALIZE_EVENT ) ).andReturn ( nullEvents );
        
        expect ( abcGuard.test ( toBC ) ).andReturn ( true );
        expect ( aExit.apply ( toBC ) ).andReturn ( nullEvents );
        expect ( abcAction.apply ( toBC ) ).andReturn ( Collections.singletonList ( toC ) );
        expect ( bEnter.apply ( toBC ) ).andReturn ( nullEvents );
        
        expect ( bcGuard.test ( toC ) ).andReturn ( true );
        expect ( bExit.apply ( toC ) ).andReturn ( nullEvents );
        expect ( bcAction.apply ( toC ) ).andReturn ( nullEvents );
        expect ( cEnter.apply ( toC ) ).andReturn ( nullEvents );
        expect ( xEnter.apply ( toC ) ).andReturn ( nullEvents );
        expect ( pEnter.apply ( toC ) ).andReturn ( nullEvents );
        
        replayMocks();
        
        fsm.init();
        Assertions.assertEquals ( sA, fsm.currentState() );

        fsm.react ( toBC );
        Assertions.assertEquals ( sC, fsm.currentState() );
        
        verifyMocks();

    }
    
    @Test
    public void testReentrant()
    {
    }
    
    @Test
    public void testGuard()
    {
        resetMocks();
        
        expect ( aEnter.apply ( Event.INITIALIZE_EVENT ) ).andReturn ( nullEvents );
        
        expect ( abGuard.test ( toB ) ).andReturn ( false );
        
        replayMocks();
        
        fsm.init();
        Assertions.assertEquals ( sA, fsm.currentState() );

        fsm.react ( toB );
        Assertions.assertEquals ( sA, fsm.currentState() );
        
        verifyMocks();
    }
    
    @Test
    public void testChoiceTrue()
    {
        resetMocks();
        
        expect ( aEnter.apply ( Event.INITIALIZE_EVENT ) ).andReturn ( nullEvents );
        
        expect ( adGuard.test ( toD ) ).andReturn ( true );
        expect ( aExit.apply ( toD ) ).andReturn ( null );
        expect ( adAction.apply ( toD ) ).andReturn ( null );
        expect ( dPredicate.test ( toD ) ).andReturn ( true );
        expect ( dbAction.apply ( Event.TRUE_EVENT ) ).andReturn ( nullEvents );
        expect ( bEnter.apply ( Event.TRUE_EVENT ) ).andReturn ( null );
        
        replayMocks();
        
        fsm.init();
        Assertions.assertEquals ( sA, fsm.currentState() );
        
        fsm.react ( toD );
        Assertions.assertEquals ( sB, fsm.currentState() );
        
        verifyMocks();
    }
    
    @Test
    public void testChoiceFalse()
    {
        resetMocks();
        
        expect ( aEnter.apply ( Event.INITIALIZE_EVENT ) ).andReturn ( nullEvents );
        
        expect ( adGuard.test ( toD ) ).andReturn ( true );
        expect ( aExit.apply ( toD ) ).andReturn ( null );
        expect ( adAction.apply ( toD ) ).andReturn ( null );
        expect ( dPredicate.test ( toD ) ).andReturn ( false );
        expect ( dcAction.apply ( Event.FALSE_EVENT ) ).andReturn ( nullEvents );
        expect ( cEnter.apply ( Event.FALSE_EVENT ) ).andReturn ( null );
        expect ( xEnter.apply ( Event.FALSE_EVENT ) ).andReturn ( null );
        expect ( pEnter.apply ( Event.FALSE_EVENT ) ).andReturn ( null );
        
        replayMocks();
        
        fsm.init();
        Assertions.assertEquals ( sA, fsm.currentState() );
        
        fsm.react ( toD );
        Assertions.assertEquals ( sC, fsm.currentState() );
        
        verifyMocks();
    }
    
    @Test
    public void testTerminate()
    {
        resetMocks();

        expect ( aEnter.apply ( Event.INITIALIZE_EVENT ) ).andReturn ( nullEvents );
        expect ( aExit.apply ( Event.TERMINATE_EVENT ) ).andReturn ( nullEvents );

        replayMocks();
        
        fsm.init();
        Assertions.assertEquals ( sA, fsm.currentState() );
        
        fsm.terminate();
        Assertions.assertEquals ( State.TERMINATED_STATE, fsm.currentState() );

        verifyMocks();
    }
}

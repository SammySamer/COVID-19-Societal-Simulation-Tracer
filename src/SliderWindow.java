import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderWindow
{
	public int xCoordinate = 0;
	public int yCoordinate = 0;
	public int numNodes = 0;
	public int covidPercent = 0;
	public int walkLength = 0;
	public int minWaitTime = 0; 
	public int maxWaitTime = 0; 
	public int moveDistance = 0; 
	public int safeDistance = 0; 
	public int infectionTime = 0; 
	
   SliderWindow() 
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
            	Slider frame = new Slider();
                JButton button = new JButton();
                
                button.setVisible(true);
                button.setText("Set Parameters");
                frame.add(button, BorderLayout.SOUTH);
             
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    {
                    	//set initial values in case user didn't change slider
                    	if(xCoordinate == 0)
                    		xCoordinate = 30;
                    	if(yCoordinate == 0)
                    		yCoordinate = 30;
                    	if(numNodes == 0)
                    		numNodes = 30;
                    	if(covidPercent == 0)
                    		covidPercent = 10;
                    	if(minWaitTime == 0)
                    		minWaitTime = 500;
                    	if(maxWaitTime == 0)
                    		maxWaitTime = 500;
                    	if(walkLength == 0)
                    		walkLength = 60;
                    	if(moveDistance == 0)
                    		moveDistance = 1;
                    	if(safeDistance == 0)
                    		safeDistance = 1;
                    	if(infectionTime == 0)
                    		infectionTime = 2;
                    	
						frame.dispose();
						EventQueue.invokeLater(() -> {
						Main cd = new Main();
						cd.setVisible(true);
						});
                    }
                });
                
            	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
               
            }
         });
   }


class Slider extends JFrame
{
	private static final long serialVersionUID = -1020563893718121461L;

	public Slider()
   {
      setTitle("Input Parameters");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      sliderPanel = new JPanel();
      sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
         

      //---------ADD X COORDINATE SLIDER-----------
      
      JSlider xCoordSlider = new JSlider(10,30,30);
      xCoordSlider.setPaintTicks(true);
      xCoordSlider.setPaintLabels(true);
      xCoordSlider.setSnapToTicks(true);
      xCoordSlider.setMajorTickSpacing(10);
      xCoordSlider.setMinorTickSpacing(1);
      addSlider(xCoordSlider, "X Coordinate size");
      
      //xCoord Listener
      xCoordSlider.addChangeListener((ChangeEvent event) -> {
    	  xCoordinate = xCoordSlider.getValue();
      });
      
    //---------ADD Y COORDINATE SLIDER-----------
      
      JSlider yCoordSlider = new JSlider(10,30,30);
      yCoordSlider.setPaintTicks(true);
      yCoordSlider.setPaintLabels(true);
      yCoordSlider.setSnapToTicks(true);
      yCoordSlider.setMajorTickSpacing(10);
      yCoordSlider.setMinorTickSpacing(1);
      addSlider(yCoordSlider, "Y Coordinate size");
      
      //yCoord Listener
      yCoordSlider.addChangeListener((ChangeEvent event) -> {
    	  yCoordinate = yCoordSlider.getValue();
      });
      
      //---------ADD NUM NODES SLIDER-----------
      
      JSlider numNodesSlider = new JSlider(10,30,30);
      numNodesSlider.setPaintTicks(true);
      numNodesSlider.setPaintLabels(true);
      numNodesSlider.setSnapToTicks(true);
      numNodesSlider.setMajorTickSpacing(10);
      numNodesSlider.setMinorTickSpacing(1);
      addSlider(numNodesSlider, "Number of Nodes");
      
     
     //num node Listener
      numNodesSlider.addChangeListener((ChangeEvent event) -> {
    	  numNodes = numNodesSlider.getValue();
      });
      
      //---------ADD COVID PERCENT SLIDER-----------

  	  JSlider covidPercentSlider = new JSlider(10,90,10); //values from 10 - 100
      covidPercentSlider.setPaintTicks(true);
      covidPercentSlider.setPaintLabels(true);
      covidPercentSlider.setSnapToTicks(true);
      covidPercentSlider.setMajorTickSpacing(10);
      covidPercentSlider.setMinorTickSpacing(10);
      addSlider(covidPercentSlider, "Covid Percentage");
      
      //covid percent Listener
      covidPercentSlider.addChangeListener((ChangeEvent event) -> {
    	  covidPercent = covidPercentSlider.getValue();
      });
      
      //---------ADD WALK LENGTH SLIDER-----------
      
      JSlider walkLengthSlider = new JSlider(60,100,60);
      walkLengthSlider.setPaintTicks(true);
      walkLengthSlider.setPaintLabels(true);
      walkLengthSlider.setSnapToTicks(true);
      walkLengthSlider.setMajorTickSpacing(10);
      walkLengthSlider.setMinorTickSpacing(1);
      addSlider(walkLengthSlider, "Walk Length");
      
     
     //walk length Listener
      walkLengthSlider.addChangeListener((ChangeEvent event) -> {
    	  walkLength = walkLengthSlider.getValue();
      });
      
      //---------ADD MAX WAIT TIME SLIDER-----------
      JSlider minWaitTimeSlider = new JSlider(500,1000,500);
      JSlider maxWaitTimeSlider = new JSlider(500,1000,500);
      maxWaitTimeSlider.setPaintLabels(true);
      maxWaitTimeSlider.setPaintTicks(true);
      maxWaitTimeSlider.setSnapToTicks(true);
      maxWaitTimeSlider.setMajorTickSpacing(100);
      maxWaitTimeSlider.setMinorTickSpacing(100);
      addSlider(maxWaitTimeSlider, "Max wait time (ms)");
      
      maxWaitTimeSlider.addChangeListener(new ChangeListener() {
    	  public void stateChanged(ChangeEvent e) {
    		  minWaitTimeSlider.setMaximum(maxWaitTimeSlider.getValue());
    	  }
    	});
      //---------ADD MIN WAIT TIME SLIDER-----------
      minWaitTimeSlider.setPaintLabels(true);
      minWaitTimeSlider.setPaintTicks(true);
      minWaitTimeSlider.setSnapToTicks(true);
      minWaitTimeSlider.setMajorTickSpacing(100);
      minWaitTimeSlider.setMinorTickSpacing(100);
      addSlider(minWaitTimeSlider, "Min wait time (ms)");
      
     
     //walk length Listener
      minWaitTimeSlider.addChangeListener((ChangeEvent event) -> {
    	  minWaitTime = minWaitTimeSlider.getValue();
      });
      
      
     //walk length Listener
      maxWaitTimeSlider.addChangeListener((ChangeEvent event) -> {
    	  maxWaitTime = maxWaitTimeSlider.getValue();
      });
      //---------ADD MOVE DISTANCE SLIDER-----------
      JSlider moveDistanceSlider = new JSlider(1,3,1);
      moveDistanceSlider.setPaintLabels(true);
      moveDistanceSlider.setPaintTicks(true);
      moveDistanceSlider.setSnapToTicks(true);
      moveDistanceSlider.setMajorTickSpacing(1);
      moveDistanceSlider.setMinorTickSpacing(1);
      addSlider(moveDistanceSlider, "Moving distance");
     
     //move distances Listener
      moveDistanceSlider.addChangeListener((ChangeEvent event) -> {
    	  moveDistance = moveDistanceSlider.getValue();
      });
      
      //---------ADD SAFE DISTANCE SLIDER-----------
      JSlider safeDistanceSlider = new JSlider(1, 3,1);
      safeDistanceSlider.setPaintLabels(true);
      safeDistanceSlider.setPaintTicks(true);
      safeDistanceSlider.setSnapToTicks(true);
      safeDistanceSlider.setMajorTickSpacing(1);
      safeDistanceSlider.setMinorTickSpacing(1);
      addSlider(safeDistanceSlider, "Safe Distance");
     
     //safe distances Listener
      safeDistanceSlider.addChangeListener((ChangeEvent event) -> {
    	  safeDistance = safeDistanceSlider.getValue();
      });
      
      //---------ADD INFECTION TIME SLIDER-----------
      JSlider infectionTimeSlider = new JSlider(1, 4, 2);
      infectionTimeSlider.setPaintLabels(true);
      infectionTimeSlider.setPaintTicks(true);
      infectionTimeSlider.setSnapToTicks(true);
      infectionTimeSlider.setMajorTickSpacing(1);
      infectionTimeSlider.setMinorTickSpacing(1);
      addSlider(infectionTimeSlider, "Infection time (sec)");
      
     //safe distances Listener
      infectionTimeSlider.addChangeListener((ChangeEvent event) -> {
    	  infectionTime = infectionTimeSlider.getValue();
      });
     
      add(sliderPanel, BorderLayout.CENTER);
   
   }
   public void addSlider(JSlider s, String description)
   {
//      s.addChangeListener(listener);
      JPanel panel = new JPanel();
      panel.add(s);
      panel.add(new JLabel(description));
      sliderPanel.add(panel);
   }

   public static final int DEFAULT_WIDTH = 350;
   public static final int DEFAULT_HEIGHT = 900;

   private JPanel sliderPanel;
   public int value;
}


}
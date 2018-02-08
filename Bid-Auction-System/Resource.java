/*
 *
 *   Program Developed By Eryk Szlachetka
 *   This class is responsible for the item auctioned on the service.
 *
 */
class Resource
{
    // Declare variables
    private int timeLeft;
    private int currentBid;
    private String productDescription;
    private boolean auctionOpen;
    private boolean sold;
    private int id;
    
    // Constructor, accepts id and description as parameter, passed from the main class
	public Resource(int passedId, String desc)
	{
        // Init the variables
        currentBid = 1;
        productDescription = desc;
        timeLeft = 60;
        auctionOpen = true;
        sold = false;
        id = passedId;
	}

    
    // Setter method to set a new highest bid.
    public synchronized int setNewBid(int newBid){
        try{
            while(!auctionOpen){
                wait();
            }
            System.out.println("New bid placed: " + newBid + "\n");
            currentBid = newBid;
            notify();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        return currentBid;
    }
    // Setter method to set a new time.
    public synchronized int setNewTime(){
        timeLeft = 20;
        System.out.println("New time set: " + timeLeft + "\n");
        notify();
        return timeLeft;
    }
    
    // Set the current item as sold
    public void setToSold(){
        auctionOpen = false;
        sold = true;
    }
    
    // Start the auction
    public void enableAuction(){
        auctionOpen = true;
        sold = false;
    }
    
    // Method called to decrease the time
    public synchronized int decreaseTime(){
        try{
            while(timeLeft <= 0){
                wait();
            }
            timeLeft--;
            notify();
        }catch(InterruptedException e ){
            System.out.println(e);
        }
        return timeLeft;
    }
    
    // Getter method to receive current bid.
    public int getCurrentBid(){
        return currentBid;
    }
    
    // Getter to receive product description.
    public String getDesc(){
        return productDescription;
    }
    
    // Getter to receive time left.
    public int getTimeLeft(){
        return timeLeft;
    }
    
    // Getter to find out if item is sold
    public boolean isSold(){
        return sold;
    }
    
    public int getId(){
        return id;
    }

}

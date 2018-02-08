/*
 *
 *   Program Developed By Eryk Szlachetka
 *   This class is responsible for dealing with resource's time left
 *
 */
class TimeReducer extends Thread
{
	private Resource item[]; // items
    private Notifier notifier; // notifier
    private int currentItem;
    private boolean continueAuciton; // boolean to end the program

    // Constructor, accepts the resource array and notifier that keeps track of clients
	public TimeReducer(Resource resource[], Notifier notifier)
	{
        // Init variables
		item = resource;
        this.notifier = notifier;
        currentItem = 0;
        continueAuciton = true;
	}

    // Method that runs the thread in the background, extented from Thread class
	public void run()
	{
		int newTime; // the time

        // Do while the last item is not sold
		do
		{
            // Get current auction
            currentItem = notifier.getCurrentAuction();
			try
			{
                newTime = item[currentItem].decreaseTime(); // Decrease time by 1, every second.
                
                // Check if auction time have expired
                if(newTime <= 0){
                    
                    // Check if someone has bet for this item
                    // If so then notify all the clients and set the item as sold
                    if(item[currentItem].getCurrentBid() > 1){
                        notifier.notifyAll(item[currentItem].getDesc() + "%0%" +
                                       String.valueOf(item[currentItem].getCurrentBid()) + "% ---- ITEM SOLD ---");
                        item[currentItem].setToSold();
                    }
                    
                    int temp = currentItem;
                    // Now grab any item that is not sold yet in order to auction it.
                    // Iterate through the auction items
                    for(Resource r : item){
                        // Check if item that is not sold found
                        if(!r.isSold() && r != item[currentItem]){
                            // If so, set the current auction item to that item's number.
                            if(currentItem < 5){
                                notifier.setCurrentAuction(r.getId());
                                item[r.getId()].setNewTime();
                                currentItem = r.getId();
                                break;
                            }
                        }
                    }
                        
                    // If this gets executed then that means we are dealing with last resource.
                    // There is no need incrementing the currentItem, but the time has to be reset.
                    if(currentItem == temp){
                        // If the last item is sold then exit
                        if(item[currentItem].isSold()){
                            continueAuciton = false;
                        }
                        // Otherwise, auction it
                        item[currentItem].setNewTime();
                    }
                }else{
                    // If auction time have not expired then notify clients about time left
                    notifier.notifyAll(item[currentItem].getDesc() + "%" + String.valueOf(item[currentItem].getTimeLeft()) + "%" + String.valueOf(item[currentItem].getCurrentBid()) + "% ");
                }
                
                System.out.println("New time: " + newTime);
                sleep(1000);
			}
			catch (InterruptedException interruptEx)
			{
				System.out.println(interruptEx.toString());
			}
		}while (true && continueAuciton == true);
	}
}

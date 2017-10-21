public class Player{
    private int number;
    private int location;

    public Player(int number){
    	this.number = number;
    	this.location = 0;
    }

    public Player(int number, int loc){
        this.number = number;
        this.location = location;
    }

    public void setNumber(int number){
    	this.number = number;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public int getNumber(){
    	return number;
    }

    public int getLocation(){
    	return location;
    }
}

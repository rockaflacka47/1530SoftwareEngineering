public class Player{
    int playerNum;
    int location;

    public Player(int num, int loc){
        playerNum = num;
        location = loc;
    }

    public void Move(int newLoc){
        location = newLoc;
    }
}
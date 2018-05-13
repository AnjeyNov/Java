package client;

public class FirstClass extends Passenger {

    public FirstClass(){
        super();
    }

    private void downHunger(int k){
        hunger -=k;
        if(hunger < 0)
            hunger = 0;
        hunger_lbl.setText("Голод: " + Integer.toString(hunger));
    }

    public void eat(int k){
        downHunger(k);
    }
}

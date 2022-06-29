enum busState {
    running,
    inGarage
}

public class Bus extends Vehicle {
    public int busNum;
    public int charge = 0;
    public int curPassenger = 0;
    public int maxPassenger = 100;
    public float oil = 0;
    public float speed = 0;
    public busState state = busState.running;


    public Bus(int num) {
        this.busNum = num;
        state = busState.running;
    }

    public void setState(busState nextState)
    {
        state = nextState;
    }

    public void run(float distance)
    {
        //100m당 1l감소
        oil -= distance / 100;
        if (oil < 10)
            System.out.println("주유가 필요하다.");
        if (oil <= 0)
            setState(busState.inGarage);
    }

    public void take(int passenger) {
        if(!(state == busState.running))
            return;
        if(curPassenger + passenger <= maxPassenger)
            curPassenger += passenger;
        else
            curPassenger = maxPassenger;
        charge += 1000;
    }

}


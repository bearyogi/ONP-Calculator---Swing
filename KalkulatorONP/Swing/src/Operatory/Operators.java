package Operatory;

public abstract class Operators {
     int priority;
     char sign;

    public boolean priorityCheck(Operators mark){
        if (this.priority <= mark.priority)
            return true;
        else
            return false;
    }

    public boolean notLBracket(){
        if (this.sign != '(')
            return true;
        else
            return false;
    }


    public String toString() {
        return String.valueOf(sign);
    }
}

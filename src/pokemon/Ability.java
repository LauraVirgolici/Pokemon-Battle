package pokemon;

public class Ability {

    int dmg;   //Damage
    boolean stun;
    boolean dodge;
    int cd;   //Cooldown
    int cdReset;
    int activated;

    public Ability(int dmg, boolean stun, boolean dodge, int cd) {
        this.dmg = dmg;
        this.stun = stun;
        this.dodge = dodge;
        this.cd = cd;
        this.cdReset = cd;
        this.activated=0;
    }

    @Override
    public String toString() {
        return "{" +
                "dmg=" + dmg +
                ", stun=" + stun +
                ", dodge=" + dodge +
                ", cd=" + cd +
                '}';
    }


    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public boolean isStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean isDodge() {
        return dodge;
    }

    public void setDodge(boolean dodge) {
        this.dodge = dodge;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public void countCd() {
        setCd(this.getCd() - 1);
    }


    public boolean checkCd() {
        if (this.cd == 0) {
            this.cd = cdReset;
            return true;
        }
        return false;

    }

    public void resetCd(){
        this.cd=cdReset;
    }

    public int getCdReset() {
        return cdReset;
    }

    public void setCdReset(int cdReset) {
        this.cdReset = cdReset;
    }
}

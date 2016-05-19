package Main.Domain;

/**
 * Object that stores the name of a friend and the sum he has overpayed/underpayed.
 *
 * */
public class Balance {

    String friendName;
    Double balance;

    public Balance(String friendName, Double balance) {
        this.friendName = friendName;
        this.balance = balance;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

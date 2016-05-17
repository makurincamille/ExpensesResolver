package Main.Domain;

import java.math.BigDecimal;


public class Purchase {

    public String friendName;
    public String purchaseDescription;
    public Double cost;

    public Purchase(String friendName, String purchaseDescription, Double cost) {

        this.friendName = friendName;
        this.purchaseDescription = purchaseDescription;
        this.cost = cost;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getPurchaseDescription() {
        return purchaseDescription;
    }

    public void setPurchaseDescription(String purchaseDescription) {
        this.purchaseDescription = purchaseDescription;
    }
}

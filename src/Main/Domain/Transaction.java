package Main.Domain;


/**
 * debitor - the person who has underpayed and has to send money to creditor
 * creditor - the person who has underpayed. Has to receive money from debitor
 * */
public class Transaction {

    String debitorName;
    String creditorName;
    Double amount;

    public Transaction(String debitorName, String creditorName, Double amount) {
        this.debitorName = debitorName;
        this.creditorName = creditorName;
        this.amount = amount;
    }

    @Override
    public String toString(){
        return this.debitorName + " has to pay " + this.creditorName + " " + this.amount + "$.";

    }

    public String getDebitorName() {
        return debitorName;
    }

    public void setDebitorName(String debitorName) {
        this.debitorName = debitorName;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

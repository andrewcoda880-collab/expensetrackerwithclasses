
public class Bills{

    private String bill;
    private double totalPrice;
    private String inputFrequencyForBills;
    private double frequencyAmountForBills;

    

    public Bills(String bill, double totalPrice, String inputFrequencyForBills, double frequencyAmountForBills) {
        this.bill = bill;
        this.totalPrice = totalPrice;
        this.inputFrequencyForBills = inputFrequencyForBills;
        this.frequencyAmountForBills = frequencyAmountForBills;
    }
        public String getBills() {
            return bill;
        }
        public double getTotalPrice() {
            return totalPrice;
        }
        public String getInputFrequency() {
            return inputFrequencyForBills;
        }

        public double getFrequencyAmount(){
           return frequencyAmountForBills;
        }

        
}

public class Subscription{

    private String subscription;
    private double totalPrice;
    private String inputFrequencyForSubscription;
    private double frequencyAmountForSubscription;

    

    public Subscription(String subscription, double totalPrice, String inputFrequencyForSubscrption, double frequencyAmountForSubscription) {
        this.subscription = subscription;
        this.totalPrice = totalPrice;
        this.inputFrequencyForSubscription = inputFrequencyForSubscrption;
        this.frequencyAmountForSubscription = frequencyAmountForSubscription;
    }
        public String getSubscription() {
            return subscription;
        }
        public double getTotalPrice() {
            return totalPrice;
        }
        public String getInputFrequency() {
            return inputFrequencyForSubscription;
        }

        public double getFrequencyAmount(){
           return frequencyAmountForSubscription;
        }

        
}
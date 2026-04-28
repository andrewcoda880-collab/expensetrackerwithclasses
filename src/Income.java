
public class Income{

    private String source;
    private double totalIncome;
    private String inputFrequency;
    private double frequencyAmount;

    

    public Income(String source, double totalIncome, String inputFrequency, double frequencyAmount) {
        this.source = source;
        this.totalIncome = totalIncome;
        this.inputFrequency = inputFrequency;
        this.frequencyAmount = frequencyAmount;
    }
        public String getSource() {
            return source;
        }
        public double getTotalIncome() {
            return totalIncome;
        }
        public String getInputFrequency() {
            return inputFrequency;
        }

        public double getFrequencyAmount(){
           return frequencyAmount;
        }
}
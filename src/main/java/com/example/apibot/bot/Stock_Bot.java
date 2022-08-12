package bot;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor


public class Stock_Bot {
    private int companyId;
    private String companyName;
    private String companyTitle;
    private String DateTime;

    public Stock_Bot(int companyId, String companyName, String companyTitle, String DateTime) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyTitle = companyTitle;
        this.DateTime = DateTime;
    }

    public int getCompanyId() {
        return companyId;
    }


    public String getCompanyName() {
        return companyName;
    }


    public String getCompanyTitle() {
        return companyTitle;
    }


    public String getDateTime() {
        return DateTime;
    }


    @Override
    public String toString() {
        return "Stock_Bot{" +
                "companyId=" + this.getCompanyId() +
                ", companyName='" + this.getCompanyName() + '\'' +
                ", companyTitle='" + this.getCompanyTitle() + '\'' +
                ", DateTime='" + this.getDateTime() + '\'' +
                '}';
    }
}





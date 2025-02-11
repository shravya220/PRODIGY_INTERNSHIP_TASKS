import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PRODIGY_SD_05 {

    public static void main(String[] args) {
        // Replace this URL with the actual URL of the e-commerce website you want to scrape
        String url = "https://example-ecommerce-website.com/products";

        // Specify the CSV file path
        String csvFilePath = "product_data.csv";

        try {
            // Fetch and parse the HTML document
            Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .timeout(10 * 1000) // 10 seconds timeout
                    .get();

            Elements products = document.select(".product"); // Adjust the selector based on the actual HTML structure

            if (products.isEmpty()) {
                System.out.println("No products found. Please check the CSS selector.");
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                // Write CSV header
                writer.write("Product Name,Price,Rating\n");

                // Extract and write product information to CSV
                for (Element product : products) {
                    String productName = product.select(".product-name").text().replaceAll(",", " ");
                    String price = product.select(".product-price").text().replaceAll(",", " ");
                    String rating = product.select(".product-rating").text().replaceAll(",", " ");

                    writer.write(productName + "," + price + "," + rating + "\n");
                }

                System.out.println("Data successfully scraped and saved to " + csvFilePath);
            }
        } catch (IOException e) {
            System.err.println("Error connecting to the website or writing to the CSV file: " + e.getMessage());
        }
    }
}

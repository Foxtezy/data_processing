package ru.nsu.makhov.prices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainPrices {

    private static final Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "airflights", "airflights");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.execute("DROP TABLE IF EXISTS bookings.prices");
            st.execute("CREATE TABLE bookings.prices (flight_no varchar(10), fare_conditions varchar(10), extra_space bool, amount int)");
        }
        List<FlightPrice> prices = getPrices();
        try (Statement st = con.createStatement()) {
            for (FlightPrice price : prices) {
                if (price.maxAmount() == price.minAmount()) {
                    st.executeUpdate(String.format("INSERT INTO bookings.prices VALUES ('%s', '%s', %b, %d)",
                            price.flightNo(), price.fareConditions(), false, price.minAmount()));
                } else {
                    st.executeUpdate(String.format("INSERT INTO bookings.prices VALUES ('%s', '%s', %b, %d)",
                            price.flightNo(), price.fareConditions(), false, price.minAmount()));
                    st.executeUpdate(String.format("INSERT INTO bookings.prices VALUES ('%s', '%s', %b, %d)",
                            price.flightNo(), price.fareConditions(), true, price.maxAmount()));
                }
            }
        }

    }

    private static List<FlightPrice> getPrices() {
        try (Statement st = con.createStatement()) {
            ResultSet result = st.executeQuery("""
                    SELECT DISTINCT f.flight_no, tf.fare_conditions, min(tf.amount) as min_amount, max(tf.amount) as max_amount
                    FROM flights as f
                    JOIN bookings.ticket_flights tf on f.flight_id = tf.flight_id
                    GROUP BY f.flight_no, tf.fare_conditions
                    ORDER BY flight_no""");
            List<FlightPrice> prices = new ArrayList<>();
            while (result.next()) {
                prices.add(new FlightPrice(result.getString("flight_no"),
                        result.getString("fare_conditions"),
                        result.getInt("min_amount"),
                        result.getInt("max_amount")));
            }
            return prices;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

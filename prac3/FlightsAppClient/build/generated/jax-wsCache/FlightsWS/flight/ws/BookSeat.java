
package flight.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for book_seat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="book_seat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_vuelo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "book_seat", propOrder = {
    "idVuelo",
    "date"
})
public class BookSeat {

    @XmlElement(name = "id_vuelo")
    protected int idVuelo;
    protected int date;

    /**
     * Gets the value of the idVuelo property.
     * 
     */
    public int getIdVuelo() {
        return idVuelo;
    }

    /**
     * Sets the value of the idVuelo property.
     * 
     */
    public void setIdVuelo(int value) {
        this.idVuelo = value;
    }

    /**
     * Gets the value of the date property.
     * 
     */
    public int getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     */
    public void setDate(int value) {
        this.date = value;
    }

}

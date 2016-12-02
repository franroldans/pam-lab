
package flight.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the flight.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BookSeat_QNAME = new QName("http://ws.flight/", "book_seat");
    private final static QName _BookSeatResponse_QNAME = new QName("http://ws.flight/", "book_seatResponse");
    private final static QName _QueryFree_QNAME = new QName("http://ws.flight/", "query_free");
    private final static QName _QueryFreeResponse_QNAME = new QName("http://ws.flight/", "query_freeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: flight.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BookSeat }
     * 
     */
    public BookSeat createBookSeat() {
        return new BookSeat();
    }

    /**
     * Create an instance of {@link BookSeatResponse }
     * 
     */
    public BookSeatResponse createBookSeatResponse() {
        return new BookSeatResponse();
    }

    /**
     * Create an instance of {@link QueryFree }
     * 
     */
    public QueryFree createQueryFree() {
        return new QueryFree();
    }

    /**
     * Create an instance of {@link QueryFreeResponse }
     * 
     */
    public QueryFreeResponse createQueryFreeResponse() {
        return new QueryFreeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookSeat }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.flight/", name = "book_seat")
    public JAXBElement<BookSeat> createBookSeat(BookSeat value) {
        return new JAXBElement<BookSeat>(_BookSeat_QNAME, BookSeat.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookSeatResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.flight/", name = "book_seatResponse")
    public JAXBElement<BookSeatResponse> createBookSeatResponse(BookSeatResponse value) {
        return new JAXBElement<BookSeatResponse>(_BookSeatResponse_QNAME, BookSeatResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryFree }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.flight/", name = "query_free")
    public JAXBElement<QueryFree> createQueryFree(QueryFree value) {
        return new JAXBElement<QueryFree>(_QueryFree_QNAME, QueryFree.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryFreeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.flight/", name = "query_freeResponse")
    public JAXBElement<QueryFreeResponse> createQueryFreeResponse(QueryFreeResponse value) {
        return new JAXBElement<QueryFreeResponse>(_QueryFreeResponse_QNAME, QueryFreeResponse.class, null, value);
    }

}

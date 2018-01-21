
package com.day_28.station.cxfservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.day_28.station.cxfservice package. 
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

    private final static QName _BuyTicketResponse_QNAME = new QName("http://cxfService.station.day_28.com/", "buyTicketResponse");
    private final static QName _LockTicket_QNAME = new QName("http://cxfService.station.day_28.com/", "lockTicket");
    private final static QName _LockTicketResponse_QNAME = new QName("http://cxfService.station.day_28.com/", "lockTicketResponse");
    private final static QName _QueryAllTicketResponse_QNAME = new QName("http://cxfService.station.day_28.com/", "queryAllTicketResponse");
    private final static QName _BuyTicket_QNAME = new QName("http://cxfService.station.day_28.com/", "buyTicket");
    private final static QName _QueryAllTicket_QNAME = new QName("http://cxfService.station.day_28.com/", "queryAllTicket");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.day_28.station.cxfservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BuyTicketResponse }
     * 
     */
    public BuyTicketResponse createBuyTicketResponse() {
        return new BuyTicketResponse();
    }

    /**
     * Create an instance of {@link LockTicket }
     * 
     */
    public LockTicket createLockTicket() {
        return new LockTicket();
    }

    /**
     * Create an instance of {@link LockTicketResponse }
     * 
     */
    public LockTicketResponse createLockTicketResponse() {
        return new LockTicketResponse();
    }

    /**
     * Create an instance of {@link QueryAllTicketResponse }
     * 
     */
    public QueryAllTicketResponse createQueryAllTicketResponse() {
        return new QueryAllTicketResponse();
    }

    /**
     * Create an instance of {@link BuyTicket }
     * 
     */
    public BuyTicket createBuyTicket() {
        return new BuyTicket();
    }

    /**
     * Create an instance of {@link QueryAllTicket }
     * 
     */
    public QueryAllTicket createQueryAllTicket() {
        return new QueryAllTicket();
    }

    /**
     * Create an instance of {@link Ticket }
     * 
     */
    public Ticket createTicket() {
        return new Ticket();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfService.station.day_28.com/", name = "buyTicketResponse")
    public JAXBElement<BuyTicketResponse> createBuyTicketResponse(BuyTicketResponse value) {
        return new JAXBElement<BuyTicketResponse>(_BuyTicketResponse_QNAME, BuyTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LockTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfService.station.day_28.com/", name = "lockTicket")
    public JAXBElement<LockTicket> createLockTicket(LockTicket value) {
        return new JAXBElement<LockTicket>(_LockTicket_QNAME, LockTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LockTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfService.station.day_28.com/", name = "lockTicketResponse")
    public JAXBElement<LockTicketResponse> createLockTicketResponse(LockTicketResponse value) {
        return new JAXBElement<LockTicketResponse>(_LockTicketResponse_QNAME, LockTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryAllTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfService.station.day_28.com/", name = "queryAllTicketResponse")
    public JAXBElement<QueryAllTicketResponse> createQueryAllTicketResponse(QueryAllTicketResponse value) {
        return new JAXBElement<QueryAllTicketResponse>(_QueryAllTicketResponse_QNAME, QueryAllTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfService.station.day_28.com/", name = "buyTicket")
    public JAXBElement<BuyTicket> createBuyTicket(BuyTicket value) {
        return new JAXBElement<BuyTicket>(_BuyTicket_QNAME, BuyTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryAllTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cxfService.station.day_28.com/", name = "queryAllTicket")
    public JAXBElement<QueryAllTicket> createQueryAllTicket(QueryAllTicket value) {
        return new JAXBElement<QueryAllTicket>(_QueryAllTicket_QNAME, QueryAllTicket.class, null, value);
    }

}


package ch.bfh.ti.sed.patmon1.ws.bindings;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (ch.bfh.ti.sed.patmon1.bind.DateAdapter.parseDateTime(value));
    }

    public String marshal(Date value) {
        return (ch.bfh.ti.sed.patmon1.bind.DateAdapter.printDateTime(value));
    }

}

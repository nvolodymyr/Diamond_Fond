package Entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Diamond {
    @XmlElementWrapper(name = "gems")
    @XmlElement(name = "gem")
    private List<Gem> gems;

    public Diamond(List<Gem> gems) {
        this.gems = gems;
    }

    public List<Gem> getGems() {
        return gems;
    }

    public void setGems(List<Gem> gems) {
        this.gems = gems;
    }

    public void addGem(Gem gem) {
        gems.add(gem);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Diamond{");
        sb.append("gems=").append(gems);
        sb.append('}');
        return sb.toString();
    }

//    public static Pavilion newInstance() {
//        return new Pavilion(null);
//    }

}

package Entity;

import javax.xml.bind.annotation.XmlElement;

public class VisualParameters {
    private String visualId;
    private String color;
    private Integer transparency;
    private Integer gemCutting;

    @XmlElement
    public void setColor(String color) {

        this.color = color;
    }


    @XmlElement
    public void setVisualId(String visualId) {
        this.visualId = visualId;
    }

    @XmlElement
    public void setTransparency(int transparency) {
        if (transparency < 0 || transparency > 100) {
            throw new IllegalArgumentException("Transparency can't be lower than 0 and higher than 100");
        } else
            this.transparency = transparency;
    }

    @XmlElement
    public void setGemCutting(int gemCutting) {
        if (gemCutting < 4 || gemCutting > 15) {
            throw new IllegalArgumentException("Cut faces should be between 4 and 15 inclusive");
        } else
            this.gemCutting = gemCutting;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VisualParameters{");
        sb.append("visualId='").append(visualId).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", transparency=").append(transparency);
        sb.append(", gemCutting=").append(gemCutting);
        sb.append('}');
        return sb.toString();
    }

    public String getColor() {
        return color;
    }

    public int getTransparency() {
        return transparency;
    }

    public int getGemCutting() {
        return gemCutting;
    }

    public String getVisualId() {
        return visualId;
    }
}

package classes;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author BoBo
 */
public class LinkLabelData extends LabelData {
    
    public LinkLabelData() {
        super();
        initLinkEffects();
    }
    
    public LinkLabelData(String label) {
        super(label);
        initLinkEffects();
    }
    
    public LinkLabelData(String label, Integer id) {
        super(label, id);
        initLinkEffects();
    }
    
    public LinkLabelData(String label, Hashtable<String, Object> data) {
        super(label, data);
        initLinkEffects();
    }

    private void initLinkEffects() {
        setFont(new java.awt.Font("Tahoma", 1, 12));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        //Map attr = getFont().getAttributes();
        //attr.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        //setFont(new Font(attr));
        addMouseListener(new java.awt.event.MouseAdapter() {
            Font original;
            @Override
            public void mouseEntered(MouseEvent e) {
                original = e.getComponent().getFont();
                Map attributes = original.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                e.getComponent().setFont(original.deriveFont(attributes));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setFont(original);
            }
        });
    }
    
    
}

package org.uengine.modeling;

import java.io.Serializable;

import javax.persistence.Id;

import org.metaworks.*;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

/**
 * @author jyj
 */
public abstract class ElementView implements Serializable, ContextAware, Cloneable {

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    private static final long serialVersionUID = 1234L;

    public final static String WHERE_ICONIC = "iconic";
    public final static String WHERE_CANVAS = "canvas";
    public final static String WHERE_DETAIL = "detail";

    String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String parent;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    String shapeId;

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    String x;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setX(int x) {
        this.x = String.valueOf(x);
    }

    String y;

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setY(int y) {
        this.y = String.valueOf(y);
    }

    String width;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setWidth(int width) {
        this.width = String.valueOf(width);
    }

    String height;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setHeight(int height) {
        this.height = String.valueOf(height);
    }

    String fromEdge;

    public String getFromEdge() {
        return fromEdge;
    }

    public void setFromEdge(String fromEdge) {
        this.fromEdge = fromEdge;
    }

    String toEdge;

    public String getToEdge() {
        return toEdge;
    }

    public void setToEdge(String toEdge) {
        this.toEdge = toEdge;
    }

    public void addToEdge(String toEdge) {
        if (getToEdge() == null || "".equals(getToEdge())) {
            this.toEdge = toEdge;
        } else {
            this.toEdge = getToEdge() + "," + toEdge;
        }
    }

    String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    String style;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    IElement element;

    @Face(ejsPath = "dwr/metaworks/genericfaces/HiddenFace.ejs")
    public IElement getElement() {
        return element;
    }

    public void setElement(IElement element) {
        this.element = element;
    }

    transient MetaworksContext metaworksContext;

    public MetaworksContext getMetaworksContext() {
        return metaworksContext;
    }

    public void setMetaworksContext(MetaworksContext metaworksContext) {
        this.metaworksContext = metaworksContext;
    }

    public ElementView() {
        this.metaworksContext = new MetaworksContext();
    }

    public ElementView(IElement element) {
        this();
        this.setElement(element);
    }

    public void fill(Symbol symbol) {
        this.setShapeId(symbol.getShapeId());
        this.setWidth(String.valueOf(symbol.getWidth()));
        this.setHeight(String.valueOf(symbol.getHeight()));
    }

    public abstract Symbol createSymbol();
//
//    //TODO: should make interface for createSymbol()....
//    public Symbol createSymbol(String type) {
//        return null;
//    }
//
//    public Symbol createSymbol(Class<? extends Symbol> symbolType) {
//        return null;
//    }
//
//    public Symbol createSymbol(String type, Class<? extends Symbol> symbolType) {
//        return null;
//    }

    @ServiceMethod(callByContent = true, target = ServiceMethodContext.TARGET_APPEND)
    public Object duplicate() {
        return new Refresh(this, true, true);
    }

    String instStatus;

    public String getInstStatus() {
        return instStatus;
    }

    public void setInstStatus(String instStatus) {
        this.instStatus = instStatus;
    }

    String backgroundColor;

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    String viewType;

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    int propertyDialogHeight;

    public int getPropertyDialogHeight() {
        return propertyDialogHeight;
    }

    public void setPropertyDialogHeight(int propertyDialogHeight) {
        this.propertyDialogHeight = propertyDialogHeight;
    }

    int propertyDialogWidth;

    public int getPropertyDialogWidth() {
        return propertyDialogWidth;
    }

    public void setPropertyDialogWidth(int propertyDialogWidth) {
        this.propertyDialogWidth = propertyDialogWidth;
    }

    @ServiceMethod(callByContent = true, eventBinding = EventContext.EVENT_DBLCLICK, target = ServiceMethodContext.TARGET_POPUP)
    public Object showProperty() throws Exception {
        return new PropertySettingDialog(this);
    }

    /**
     * convert main object from RelationView to IRelation
     *
     * @return IRelation which have this class as relationView
     */
    public IElement asElement() {
        IElement element = getElement();
        setElement(null);
        element.setElementView(this);
        return element;
    }

}
package com.osrapi.models.util;

public class Link {
    private String href;
    private String method;
    private String rel;
    public Link() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Link(final String rel, final String href, final String method) {
        super();
        this.rel = rel;
        this.href = href;
        this.method = method;
    }
    /**
     * @return the href
     */
    public String getHref() {
        return href;
    }
    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }
    /**
     * @return the rel
     */
    public String getRel() {
        return rel;
    }
    /**
     * @param href the href to set
     */
    public void setHref(final String href) {
        this.href = href;
    }
    /**
     * @param method the method to set
     */
    public void setMethod(final String method) {
        this.method = method;
    }
    /**
     * @param rel the rel to set
     */
    public void setRel(final String rel) {
        this.rel = rel;
    }
}

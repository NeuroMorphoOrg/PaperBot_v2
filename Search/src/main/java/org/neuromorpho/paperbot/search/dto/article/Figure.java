package org.neuromorpho.paperbot.search.dto.article;

import com.mongodb.gridfs.GridFSDBFile;


public class Figure {
    
    private String legend;
    private GridFSDBFile image;

    public Figure() {
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public GridFSDBFile getImage() {
        return image;
    }

    public void setImage(GridFSDBFile image) {
        this.image = image;
    }
}

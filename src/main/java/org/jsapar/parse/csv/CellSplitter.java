package org.jsapar.parse.csv;

import java.io.IOException;

import org.jsapar.JSaParException;

public interface CellSplitter {
    
    /**
     * @param sLine The line to split
     * @return An array of all cells found on the line. An empty line should return an empty array. A line containing
     * only white-space characters is considered to be empty.
     * @throws JSaParException 
     * @throws IOException 
     */
    String[] split(String sLine) throws IOException, JSaParException;
    
    

}
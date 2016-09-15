/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.afcepf.atod.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe util
 *
 * @author ronan
 */
public final class Util {

    private Util() {
        super();
    }
    /**
     * from a set to a list
     * @param set
     * @return 
     */
    public static List<Object> retrieveListAsSet(Set<Object> set) {
        return new ArrayList<Object>(set);
    }
    public static Set<Object> retrieveSetAsList(List<Object> list) {
        return new HashSet<Object>(list);
    }
}

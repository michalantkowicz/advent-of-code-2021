package com.advent.of.code.day22;

import java.util.List;

public interface Calculation {
    Range calculate(Range a, Range b);

    List<Calculation> boundaryCuboids = List.of(
            (a, b) -> new Range(a.minx(), b.minx() , a.miny(), b.miny(), a.minz(), b.minz() ),
            (a, b) -> new Range(b.minx(), b.maxx() , a.miny(), b.miny(), a.minz(), b.minz() ),
            (a, b) -> new Range(b.maxx(), a.maxx() , a.miny(), b.miny(), a.minz(), b.minz() ),
            (a, b) -> new Range(a.minx(), b.minx() , b.miny(), b.maxy(), a.minz(), b.minz() ),
            (a, b) -> new Range(b.minx(), b.maxx() , b.miny(), b.maxy(), a.minz(), b.minz() ),
            (a, b) -> new Range(b.maxx(), a.maxx() , b.miny(), b.maxy(), a.minz(), b.minz() ),
            (a, b) -> new Range(a.minx(), b.minx() , b.maxy(), a.maxy(), a.minz(), b.minz() ),
            (a, b) -> new Range(b.minx(), b.maxx() , b.maxy(), a.maxy(), a.minz(), b.minz() ),
            (a, b) -> new Range(b.maxx(), a.maxx() , b.maxy(), a.maxy(), a.minz(), b.minz() ),

            //---

            (a, b) -> new Range(a.minx(), b.minx() , a.miny(), b.miny() , b.minz(), b.maxz() ),
            (a, b) -> new Range(b.minx(), b.maxx() , a.miny(), b.miny() , b.minz(), b.maxz() ),
            (a, b) -> new Range(b.maxx(), a.maxx() , a.miny(), b.miny() , b.minz(), b.maxz() ),
            (a, b) -> new Range(a.minx(), b.minx() , b.miny(), b.maxy() , b.minz(), b.maxz() ),
            (a, b) -> new Range(b.maxx(), a.maxx() , b.miny(), b.maxy() , b.minz(), b.maxz() ),
            (a, b) -> new Range(a.minx(), b.minx() , b.maxy(), a.maxy() , b.minz(), b.maxz() ),
            (a, b) -> new Range(b.minx(), b.maxx() , b.maxy(), a.maxy() , b.minz(), b.maxz() ),
            (a, b) -> new Range(b.maxx(), a.maxx() , b.maxy(), a.maxy() , b.minz(), b.maxz() ),

            //---

            (a, b) -> new Range(a.minx(), b.minx() , a.miny(), b.miny() , a.maxz(), b.maxz() ),
            (a, b) -> new Range(b.minx(), b.maxx() , a.miny(), b.miny() , a.maxz(), b.maxz() ),
            (a, b) -> new Range(b.maxx(), a.maxx() , a.miny(), b.miny() , a.maxz(), b.maxz() ),
            (a, b) -> new Range(a.minx(), b.minx() , b.miny(), b.maxy() , a.maxz(), b.maxz() ),
            (a, b) -> new Range(b.minx(), b.maxx() , b.miny(), b.maxy() , a.maxz(), b.maxz() ),
            (a, b) -> new Range(b.maxx(), a.maxx() , b.miny(), b.maxy() , a.maxz(), b.maxz() ),
            (a, b) -> new Range(a.minx(), b.minx() , b.maxy(), a.maxy() , a.maxz(), b.maxz() ),
            (a, b) -> new Range(b.minx(), b.maxx() , b.maxy(), a.maxy() , a.maxz(), b.maxz() ),
            (a, b) -> new Range(b.maxx(), a.maxx() , b.maxy(), a.maxy() , a.maxz(), b.maxz() )
    );
}

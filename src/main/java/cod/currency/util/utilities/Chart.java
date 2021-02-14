package cod.currency.util.utilities;

import java.util.List;

/**
 * JMA - 14/02/2021 14:37
 **/
@lombok.Data
public class Chart {
    String type;
    Data data;
}

@lombok.Data
class Dataset {
    String label;
    List<Integer> data;
    List<String> backgroundColor;
    List<String> borderColor;
    int borderWidth;
}

@lombok.Data
class Data {
    List<String> labels;
    List<Dataset> datasets;
}

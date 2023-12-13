package challenge;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day12 {

    private static final Map<Input, Long> memoizationMap = new HashMap<>();

    public void getResult1(List<String> input) {
        long possibilities = 0;
        for(String string: input) {
            String[] splitBySeriesAndNumbers = string.split("\\s+");
            String series = splitBySeriesAndNumbers[0];
            String[] numbers = splitBySeriesAndNumbers[1].split(",");
            List<Integer> groups = Arrays.asList(numbers).stream().map(p -> Integer.parseInt(p)).collect(Collectors.toList());
            possibilities = possibilities + countPossibilities(series,groups);
        }

        System.out.println("Results " + possibilities);
    }

    private String unfoldSeries(String series, int times){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times - 1; i++) {
            sb.append(series);
            sb.append("?");
        }
        sb.append(series);
        return sb.toString();
    }
    private String unfoldGroup(String groups, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times - 1; i++) {
            sb.append(groups);
            sb.append(",");
        }
        sb.append(groups);
        return sb.toString();

    }

    public void getResult2(List<String> input) {
        long possibilities = 0;
        for(String string: input) {
            String[] splitBySeriesAndNumbers = string.split("\\s+");
            String series = unfoldSeries(splitBySeriesAndNumbers[0], 5);
            String groupsUnfold= unfoldGroup(splitBySeriesAndNumbers[1], 5);
            String[] numbers = groupsUnfold.split(",");
            List<Integer> groups = Arrays.asList(numbers).stream().map(p -> Integer.parseInt(p)).collect(Collectors.toList());
            possibilities = possibilities + countPossibilities(series,groups);
        }

        System.out.println("Results " + possibilities);

    }

    private long countPossibilities(String series, List<Integer> groups) {
        Input input = new Input(series, groups);
        if (memoizationMap.containsKey(input)) {
            return memoizationMap.get(input);
        }
        if (series.isBlank()) {
            return groups.isEmpty() ? 1 : 0;
        }
        char firstChar = series.charAt(0);
        long possibilities = 0;
        if (firstChar == '.') {
            possibilities = countPossibilities(series.substring(1), groups);
        } else if (firstChar == '?') {
            possibilities = countPossibilities("." + series.substring(1), groups)
                    + countPossibilities("#" + series.substring(1), groups);
        } else {
            // First character is #
            if (groups.size() == 0) {
                possibilities = 0;
            } else {
                int damagedCount = groups.get(0);
                if (damagedCount <= series.length()
                        && series.chars().limit(damagedCount).allMatch(c -> c == '#' || c == '?')) {
                    List<Integer> newGroups = groups.subList(1, groups.size());
                    if (damagedCount == series.length()) {
                        possibilities = newGroups.isEmpty() ? 1 : 0;
                    } else if (series.charAt(damagedCount) == '.') {
                        possibilities = countPossibilities(series.substring(damagedCount + 1), newGroups);
                    } else if (series.charAt(damagedCount) == '?') {
                        possibilities = countPossibilities("." + series.substring(damagedCount + 1), newGroups);
                    } else {
                        possibilities = 0;
                    }
                } else {
                    // Either size of the group is greater than the remaining length of the
                    // condition or the next nrDamaged springs are not either damaged (#) or unknown
                    // (?).
                    possibilities = 0;
                }
            }
        }
        memoizationMap.put(input, possibilities);
        return possibilities;
    }

    public class Input {
        String series;
        List<Integer> groups;

        public Input(String series, List<Integer>groups){
            this.series = series;
            this.groups = groups;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Input input = (Input) o;
            return Objects.equals(series, input.series) && Objects.equals(groups, input.groups);
        }

        @Override
        public int hashCode() {
            return Objects.hash(series, groups);
        }
    }
}

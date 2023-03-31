import com.acepero13.research.bnfreplacer.core.Replacer;
import com.acepero13.research.bnfreplacer.core.reports.model.Report;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path source = Path.of("C:\\Users\\cepero\\Documents\\NewProjects\\bnf-merger\\tmp\\en_grammar_en.bnf");
        Path destination = Path.of("C:\\Users\\cepero\\Documents\\NewProjects\\bnf-merger\\tmp\\DA_en.bnf");
        Replacer replacer = Replacer.of(source, destination);

        List<String> result = replacer.replaceAll();

        Report report = replacer.report();

        System.out.println("\n\n----------------------- REPORT --------------------\n");
        System.out.println("Number of insertions: " + report.totalInsertions());
        System.out.println("Number of updates: " + report.totalUpdates());
        System.out.println("Number of changes: " + report.totalChanges() + "\n");

        report.forEach(System.out::println);

        System.out.println("\n\n ------------ Finished --------------------- \n");
        //Writer.save(result, Path.of("/home/alvaro/Documents/Projects/Java/bnfextractor/tmp/Result_DA_en.bnf"));

    }
}

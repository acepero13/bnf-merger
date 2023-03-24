import com.acepero13.research.bnfreplacer.core.Replacer;
import com.acepero13.research.bnfreplacer.utils.Writer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Path source = Path.of("/home/alvaro/Documents/Projects/Java/bnfextractor/tmp/en_product_search_mobility_generated_en.bnf");
        Path destination = Path.of("/home/alvaro/Documents/Projects/Java/bnfextractor/tmp/DA_en.bnf");
        Replacer replacer = Replacer.of(source, destination);

        List<String> result = replacer.replaceAll();

        Writer.save(result,Path.of("/home/alvaro/Documents/Projects/Java/bnfextractor/tmp/Result_DA_en.bnf"));

    }
}

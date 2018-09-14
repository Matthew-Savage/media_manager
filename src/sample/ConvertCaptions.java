package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class ConvertCaptions {

    enum State { Number, TimeStamp, Text }

    public void convert(Reader originalSubFile, Writer convertedSubFile) throws IOException {
        BufferedReader br = new BufferedReader(originalSubFile);
        String line;
        convertedSubFile.write("WEBVTT\n\n");
        State state=State.Number;

        while((line = br.readLine())!=null){
            switch(state) {
                case Number:
                    state=State.TimeStamp;
                    break;
                case TimeStamp:
                    convertedSubFile.write(line.replace(',', '.')+"\n");
                    state=State.Text;
                    break;
                case Text:
                    convertedSubFile.write(line+"\n");
                    if( line.length()==0)
                        state=State.Number;
                    break;
            }
        }
        convertedSubFile.close();
        originalSubFile.close();
    }
}
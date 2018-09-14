package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class IndexFile {

    public IndexFile(Controller controller) {
        this.controller = controller;
    }

    private int initCount;
    private String indexFile = "C:\\MediaManager\\seasons\\seasonIndex\\amgtestindex.html";
    private String breakoutFile = "C:\\MediaManager\\seasons\\seasonIndex\\amgtestbreakout.html";

    private Controller controller;

    public void createFiles(int epCount) {
        seasonIndex(epCount);
        seasonBreakout(epCount);
    }

    private void seasonIndex(int epCount) {

        String epCountFinal = String.format("%03d", epCount);
        StringBuilder seriesIndex = new StringBuilder();
        File index = new File(indexFile);

        seriesIndex.append("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                "<title>PlushieTube - Because Fuck DMCA</title>\n" +
                "<meta name=\"viewport\" content=\"width=device-width\">\n" +
                "<link rel=\"stylesheet\" href=\"http://192.168.2.177/manga/css/style4.css\">\n" +
                "<link href=\"../../../jsplayer/video-js.css\" rel=\"stylesheet\">\n" +
                "<link href=\"../../../jsplayer/videojs-playlist-ui.vertical.css\" rel=\"stylesheet\">\n" +
                "<script src=\"../../../jsplayer/video.js\"></script>\n" +
                "<script src=\"../../../jsplayer/videojs-playlist.js\"></script>\n" +
                "<script src=\"../../../jsplayer/videojs-playlist-ui.js\"></script>\n" +
                "<script>\n" +
                "function myFunction() {\n" +
                "    window.open(\"breakout.html\", \"_blank\", \"resizable=yes,top=0,right=0,width=1102,height=620\");\n" +
                "}\n" +
                "</script>\n" +
                "<style>\n" +
                "div.outer {\n" +
                "position: relative;\n" +
                "}\n" +
                "\n" +
                "div.vjs-playlist {\n" +
                "opacity: 0;\n" +
                "    -webkit-transition: all 0.4s;\n" +
                "    transition: all 0.4s;\n" +
                "position: absolute;\n" +
                "top: 0px;\n" +
                "right: 0px;\n" +
                "width: 100%;\n" +
                "height; 600px;\n" +
                "}\n" +
                "\n" +
                "div.vjs-playlist:hover {\n" +
                "opacity: 1.0;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"wrapper\" style=\"text-align: center\">  \n" +
                "<div id=\"main\" role=\"main\">\n" +
                "<div id=\"content\" class=\"demo\">\n" +
                "<ul id=\"container\">\n" +
                "\t<li>\n" +
                "<div class=\"outer\">\n" +
                "<video id=\"video\" class=\"video-js\" autoplay=\"true\" controls preload=\"auto\" width=\"1280\" height=\"720\">\n" +
                "</video>\n" +
                "<div class=\"vjs-playlist\">\n" +
                "      <!--\n" +
                "        The contents of this element will be filled based on the\n" +
                "        currently loaded playlist\n" +
                "      -->\n" +
                "    </div>\n" +
                "</div>\n" +
                "\t</li>\n" +
                "</ul>\n" +
                "<ul>\n" +
                "<li2>\n" +
                "<a href=\"http://video.plushiearmy.com\"><img src=\"http://192.168.2.177/resources/button0.png\" /></a>\n" +
                "</li2>\n" +
                "<li2>\n" +
                "<a onclick=\"myFunction()\"><img src=\"http://192.168.2.177/resources/button1.png\" /></a>\n" +
                "</li2>\n" +
                "</ul>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<script>\n" +
                "    var player = videojs('video');\n" +
                "    player.playlist([{\n");

        seriesIndex.append(eachEp(epCount));

        seriesIndex.append("name: 'Episode " + initCount + "',\n" +
                "      sources: [\n" +
                "        { src: '" + epCountFinal + ".mp4', type: 'video/mp4' },\n" +
                "      ],\n" +
                "      \"textTracks\":[ \n" +
                "       { \"kind\":\"captions\", \"label\":\"English\", \"src\":\"" + epCountFinal + ".vtt\", \"default\":true } \n" +
                "      ]," +
                "      thumbnail: [\n" +
                "        {\n" +
                "          src: ''\n" +
                "        }\n" +
                "      ]\n" +
                "    }]);\n" +
                "    // Initialize the playlist-ui plugin with no option (i.e. the defaults).\n" +
                "    player.playlistUi({horizontal: true});\n" +
                "    player.playlist.autoadvance(0);\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>");
        writeFiles(seriesIndex, index);
    }

    private void seasonBreakout(int epCount) {

        String epCountFinal = String.format("%03d", epCount);
        StringBuilder seriesBreakout = new StringBuilder();
        File breakout = new File(breakoutFile);

        seriesBreakout.append("<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                "<title>PlushieTube - Because Fuck DMCA</title>\n" +
                "<meta name=\"viewport\" content=\"width=device-width\">\n" +
                "<link rel=\"stylesheet\" href=\"http://192.168.2.177/manga/css/style4.css\">\n" +
                "<link href=\"../../../jsplayer/video-js.css\" rel=\"stylesheet\">\n" +
                "<link href=\"../../../jsplayer/videojs-playlist-ui.vertical.css\" rel=\"stylesheet\">\n" +
                "<script src=\"../../../jsplayer/video.js\"></script>\n" +
                "<script src=\"../../../jsplayer/videojs-playlist.js\"></script>\n" +
                "<script src=\"../../../jsplayer/videojs-playlist-ui.js\"></script>\n" +
                "<style>\n" +
                ".video-container video {\n" +
                "  /* Make video to at least 100% wide and tall */\n" +
                "  min-width: 100%; \n" +
                "  min-height: 100%;\n" +
                "}\n" +
                "\n" +
                ".video-container {\n" +
                "  position: absolute;\n" +
                "  top: 0;\n" +
                "  bottom: 0;\n" +
                "  width: 100%;\n" +
                "  height: 100%; \n" +
                "  overflow: hidden;\n" +
                "}\n" +
                "\n" +
                ".video-js {\n" +
                "    width: 100%;\n" +
                "    height: 100%;\n" +
                "    }\n" +
                "div.outer {\n" +
                "position: relative;\n" +
                "width: 100%;\n" +
                "height: 100%;\n" +
                "color: white;\n" +
                "}\n" +
                "\n" +
                "div.vjs-playlist {\n" +
                "opacity: 0;\n" +
                "    -webkit-transition: all 0.4s;\n" +
                "    transition: all 0.4s;\n" +
                "position: absolute;\n" +
                "top: 0px;\n" +
                "right: 0px;\n" +
                "width: 100%;\n" +
                "height; 600px;\n" +
                "}\n" +
                "\n" +
                "div.vjs-playlist:hover {\n" +
                "opacity: 1.0;\n" +
                "}\n" +
                "\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"video-container\">\n" +
                "<div class=\"outer\">\n" +
                "<video id=\"video\" class=\"video-js\" autoplay=\"true\" controls preload=\"auto\">\n" +
                "</video>\n" +
                "<div class=\"vjs-playlist\">\n" +
                "      <!--\n" +
                "        The contents of this element will be filled based on the\n" +
                "        currently loaded playlist\n" +
                "      -->\n" +
                "    </div>\n" +
                "</div>\n" +
                "</div>\n" +
                "<script>\n" +
                "    var player = videojs('video');\n" +
                "    player.playlist([{\n");

        seriesBreakout.append(eachEp(epCount));

        seriesBreakout.append("name: 'Episode " + initCount + "',\n" +
                "      sources: [\n" +
                "        { src: '" + epCountFinal + ".mp4', type: 'video/mp4' },\n" +
                "      ],\n" +
                "      \"textTracks\":[ \n" +
                "       { \"kind\":\"captions\", \"label\":\"English\", \"src\":\"" + epCountFinal + ".vtt\", \"default\":true } \n" +
                "      ]," +
                "      thumbnail: [\n" +
                "        {\n" +
                "          src: ''\n" +
                "        }\n" +
                "      ]\n" +
                "    }]);\n" +
                "    // Initialize the playlist-ui plugin with no option (i.e. the defaults).\n" +
                "    player.playlistUi({horizontal: true});\n" +
                "    player.playlist.autoadvance(0);\n" +
                "  </script>\n" +
                "</body>\n" +
                "</html>");
        writeFiles(seriesBreakout, breakout);
    }

    private void writeFiles(StringBuilder builtString, File fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.append(builtString);
            writer.flush();
        } catch (Exception e) {
            controller.errorPopup(e.toString());
        }
    }

    private StringBuilder eachEp(int epCount) {

        StringBuilder episodes = new StringBuilder();
        initCount = 1;

        while(initCount < epCount) {
            String initCountFinal = String.format("%03d",initCount);
            episodes.append("      name: 'Episode " + initCount + "',\n" +
                    "      sources: [\n" +
                    "        { src: '" + initCountFinal + ".mp4', type: 'video/mp4' },\n" +
                    "      ],\n" +
                    "      \"textTracks\":[ \n" +
                    "       { \"kind\":\"captions\", \"label\":\"English\", \"src\":\"" + initCountFinal + ".vtt\", \"default\":true } \n" +
                    "      ]," +
                    "      thumbnail: [\n" +
                    "        {\n" +
                    "          src: ''\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },{\n");
            initCount++;
        }
        return episodes;
    }
}

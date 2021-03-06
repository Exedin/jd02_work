package web.crawler.service;

import web.crawler.model.ResultDto;
import web.crawler.model.ResultItemDto;
import web.crawler.model.SearchDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchProcessor {
    public static final Integer DEFAULT_LINK_DEEPS=8;
    public static final Integer MAX_VISITED_PAGES_LIMIT=10000;

    private final HttpLoader httpLoader = new HttpLoader();

    public ResultDto search(SearchDto searchDto) {
        //load string content by search URL
        String url=searchDto.getSeed();
        String content = httpLoader.get(searchDto.getSeed());

        //TODO: Parse string and count terms and find other URLs, calculate deep level, page URL count
        ResultDto resultDto=new ResultDto();

        //TODO: Repeat parse to max 8 level
        resultDto.getResultItemDtoList().add(parse(url, content, searchDto.getTerms()));
        return resultDto;
    }

    private ResultItemDto parse(String url, String sContent, List<String> terms) {
        ResultItemDto resultItemDto=new ResultItemDto();
        String content = sContent.toLowerCase();
        resultItemDto.setSearchUrl(url);
        for (String term:terms){
            Pattern pattern=Pattern.compile(term);
            Matcher matcher = pattern.matcher(content);
            int count=0;
            while (matcher.find()){
                count++;
            }
            resultItemDto.getTermCountMap().put(term, count);
        }
        return resultItemDto;
    }
}

package ro.tru916.web.dto;

import java.util.Set;

/**
 * Created by tudor on 07-Jun-17.
 */
public class PapersDto {
    private Set<PaperDto> papers;

    public PapersDto(Set<PaperDto> papers) {
        this.papers = papers;
    }

    public PapersDto() {
    }

    public Set<PaperDto> getPapers() { return papers; }

    public void setPapers(Set<PaperDto> papers) {
        this.papers = papers;
    }
}

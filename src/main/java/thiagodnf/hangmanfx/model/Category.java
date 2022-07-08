package thiagodnf.hangmanfx.model;

import java.util.List;

import lombok.Data;

@Data
public class Category {

	private String name;

	private List<Word> words;
}

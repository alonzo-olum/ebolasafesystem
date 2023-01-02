package ebolasafe.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.hateoas.Link;


public class ResourceSupport extends org.springframework.hateoas.ResourceSupport{
	
	@JsonProperty("_links")
	private final List<ExtendedLink> _links;
	
	public ResourceSupport(){
		super();
		this._links = new ArrayList<>();
	}
	
	@Override
	public void add(Link link) {
		if(link instanceof ExtendedLink)
			this._links.add((ExtendedLink) link);
		else
			super.add(link);
	}
	
	public List<ExtendedLink> get_links() {
		return Collections.unmodifiableList(_links);
	}

	public void remove_links() {
		_links.clear();
	}

	public Link get_link(String rel) {

		for (Link link : _links) {
			if (link.getRel().equals(rel)) {
				return link;
			}
		}

		return null;
	}
}

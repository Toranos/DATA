package DATA.services;

import java.util.List;
import java.util.UUID;

import DATA.exceptions.BadInformationException;
import DATA.model.Picture;
import DATA.model.Tag;
import DATA.model.User;
import NET.NetLocalizer;

/**
 * Service for pictures functionalities
 */
public class PictureService {
	
	private DataService data;
	
	/**
	 * Public constructor for PictureService
	 */
	public PictureService() {
		this.data = DataService.getInstance();
	}
	
	/**
	 * Retrieve local picture by its ID
	 * @param UUID of the Picture
	 * @return	The picture identified
	 * @throws BadInformationException	When incorrect information found
	 */
	public Picture getPictureById(UUID pictureUid) throws BadInformationException {
		List<Picture> pictures = data.getUser().getListPictures();
		for (Picture pic : pictures) {
			if (pic.getUid().equals(pictureUid)) {
				return pic;
			}
		}
		return null;
	}
	
	/**
	 * Retrieve local pictures by their tags
	 * @param List Tag for the picture
	 * @return	TList Picture identified
	 * @throws BadInformationException	When incorrect information found
	 */
	public List<Picture> getPictures(List<Tag> listtag) {
		return null;
	}
}

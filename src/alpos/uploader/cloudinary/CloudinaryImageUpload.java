package alpos.uploader.cloudinary;

import alpos.uploader.ImageUpload;
import com.cloudinary.Singleton;
import com.cloudinary.StoredFile;
import com.cloudinary.Transformation;

public class CloudinaryImageUpload extends StoredFile implements ImageUpload {

    public CloudinaryImageUpload() {

    }

    public String getStoredPath() {
        return this.getPreloadedFile();
    }

    public void setStoredPath(String storedPath) {
        this.setPreloadedFile(storedPath);
    }

    public String getUrl() {
        if (version != null && format != null && publicId != null) {
            return Singleton.getCloudinary().url().resourceType(resourceType).type(type).format(format).version(version)
                    .generate(publicId);
        } else {
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public String getThumbnailUrl() {
        if (version != null && format != null && publicId != null) {
            return Singleton.getCloudinary().url().format(format).resourceType(resourceType).type(type).version(version)
                    .transformation(new Transformation().width(150).height(150).crop("fit")).generate(publicId);
        } else {
            return null;
        }
    }

    public String getComputedSignature() {
        return getComputedSignature(Singleton.getCloudinary());
    }

    public boolean validSignature() {
        return getComputedSignature().equals(signature);
    }

}

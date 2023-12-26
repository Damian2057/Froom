import numpy as np
import os
import logging
from datetime import datetime
from sklearn.cluster import KMeans
from PIL import Image

logger = logging.getLogger(__name__)
TEMP_IMAGE_PATH = "TEMP_IMAGE_PATH"

def predict_color(file):
    k = 1
    image_path = TEMP_IMAGE_PATH + datetime.now().strftime("%Y%m%d-%H%M%S") + ".jpg"
    file.save(image_path)
    logger.info("Image saved to %s", image_path)

    # Load and preprocess the input image
    img = Image.open(image_path)
    img = img.resize((100, 100))  # Resize the image to speed up processing
    img_array = np.array(img)
    pixels = img_array.reshape((-1, 3))
    kmeans = KMeans(n_clusters=k)
    kmeans.fit(pixels)
    main_color = kmeans.cluster_centers_.astype(int)[0]

    os.remove(image_path)
    logger.info("Image removed from %s", image_path)

    return main_color.tolist()
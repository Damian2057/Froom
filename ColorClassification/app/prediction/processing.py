import numpy as np
import os
import logging
from datetime import datetime
from sklearn.cluster import KMeans
from PIL import Image

logger = logging.getLogger(__name__)
TEMP_IMAGE_PATH = "TEMP_IMAGE_PATH"

def predict_color(file):
    k = 2
    image_path = TEMP_IMAGE_PATH + datetime.now().strftime("%Y%m%d-%H%M%S") + ".jpg"

    try:
        file.save(image_path)
        logger.info("Image saved to %s", image_path)

        img = Image.open(image_path)
        width, height = img.size

        roi_size = min(width, height) // 2
        left = (width - roi_size) // 2
        top = (height - roi_size) // 2
        right = left + roi_size
        bottom = top + roi_size

        img = img.crop((left, top, right, bottom))
        img = img.resize((100, 100))

        img_array = np.array(img)
        pixels = img_array.reshape((-1, 3))
        kmeans = KMeans(n_clusters=k)
        kmeans.fit(pixels)
        dominant_color = kmeans.cluster_centers_.astype(int)[0]

        return dominant_color.tolist()

    finally:
        os.remove(image_path)
        logger.info("Image removed from %s", image_path)
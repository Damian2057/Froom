import logging
from flask import Blueprint, request, jsonify, Response

from app.prediction.processing import predict_color
from app.exceptions.illegal_argument_exception import IllegalArgumentException
from app.model.predicted_category import PredictedColor
from app.api.api_key_checker import check_api_key

api = Blueprint('api', __name__)
logger = logging.getLogger(__name__)


@api.route('/classify_color', methods=['POST'])
def predict():
    is_valid_api_key, error_response, status_code = check_api_key(request.headers)
    if not is_valid_api_key:
        return jsonify(error_response), status_code

    if 'file' not in request.files:
        logger.error('No file part')
        raise IllegalArgumentException('No file part')

    file = request.files['file']

    try:
        # Perform prediction
        predicted_RGB = predict_color(file)

        return jsonify(PredictedColor(predicted_RGB).__dict__)

    except Exception as e:
        logger.error('Error while predicting category: %s', str(e))
        raise IllegalArgumentException(str(e))
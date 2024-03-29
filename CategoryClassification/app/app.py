import logging

from flask import Flask
from flask_cors import CORS

from app.api.routes import api
from app.exceptions.exception_handlers import handle_500_error, handle_bad_request

app = Flask(__name__)
app.debug = True
CORS(app)

# Set up logging
logging.basicConfig(level=logging.DEBUG, force=True)

# Register the routes
app.register_blueprint(api)
app.register_error_handler(500, handle_500_error)
app.register_error_handler(Exception, handle_bad_request)

if __name__ == '__main__':
    app.run(debug=True)
var path = require("path");
var webpack = require('webpack');
var BundleTracker = require('webpack-bundle-tracker');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const devMode = process.env.NODE_ENV !== 'production';


var config = {

  context: __dirname,
  mode: "development",
  module: {
  rules: [

      {
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: "babel-loader"
        }
      },

      {
        test: /\.tsx?$/,
        use: {
          loader:'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        },
        exclude: /node_modules/,
        
      },

	{
        test: /\.(sa|sc|c)ss$/,
        use: [
          {
            loader: MiniCssExtractPlugin.loader,
          },
          'css-loader',
          'sass-loader',
        ],
      },

    ]
  },


  resolve: {
    extensions: ['*', '.js', '.jsx', '.tsx']
  }
};



var issueConfig = Object.assign({}, config, {
    name: "issue",
    entry: {
      issueMain:'./Issue/js/issue_main.tsx',
      issueView:'./Issue/js/issue_view.tsx',
      issueCreate:'./Issue/js/issue_create.tsx',
      issueLogin:'./Issue/js/issue_login.tsx',
    },
    output: {
      path: path.resolve('../src/main/resources/static/issue/'),
      filename: "[name].js",
    },
    devtool: "source-map",
    plugins: [
      new CleanWebpackPlugin(),
      new BundleTracker({filename: './Issue/webpack-stats-project.json'}),
      new MiniCssExtractPlugin({
        filename: devMode ? '[name].css' : '[name].[hash].css',
        chunkFilename: devMode ? '[id].css' : '[id].[hash].css',
      }),
    ],

});


module.exports = [issueConfig];

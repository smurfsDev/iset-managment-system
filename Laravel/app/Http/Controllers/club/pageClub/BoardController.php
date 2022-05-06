<?php

namespace App\Http\Controllers\club\pageClub;

use App\Http\Controllers\Controller;
use App\Models\board;
use Illuminate\Http\Request;
use App\Http\Requests\BoardsRequest;

class BoardController extends Controller
{
    public function getBoard(Request $request){
        $club = $request->user()->club()->get('id');
        $boards = board::where('idClub','=',$club[0]->id)->orderBy('updated_at')->paginate(5);
        if (sizeof($boards) > 0)
            return response()->json(
                $boards,
                200
            );
        else
            return response()->json([
                null
            ], 404);
    }
    public function createBoard(BoardsRequest $request){
        $club = $request->user()->club()->get('id');
        $nom = $request->input('nom');
        $post = $request->input('post');
        $image = $request->input('image');
        $idClub = $club[0]->id;

        $newBoard = array(
            "nom" => $nom,
            "post" => $post,
            "image" => $image,
            "idClub" => $idClub
        );

        $createNewBoard = board::create($newBoard);

        if ($createNewBoard) {
            return response()->json([
                'data' => [
                    'message' => 'Board created',
                    'id' => $createNewBoard->id,
                    'attributes' => $createNewBoard
                ]
            ], 201);
        }
        return "Board created";
    }

    public function deleteBoard($idBoard){

        $board = board::find($idBoard);

        if ($board){
            $board->delete();
            return response()->json([
                'type' => 'Board',
                'message' => 'Board Supprimé! '
            ], 204);
        }
        else {
            return response()->json([
                'type' => 'Board',
                'message' => 'Board non trouvée'
            ], 404);
        }
    }

    public function updateBoard($idBoard, BoardsRequest $request){

        $board = board::find($idBoard);
        if ($board) {

            $board->nom = $request->input('nom') ? $request->input('nom') : $board->nom;
            $board->post = $request->input('post') ? $request->input('post') : $board->post;
            $board->image = $request->input('image') ? $request->input('image') : $board->image;
            $board->save();
            return response()->json([
                'message' => 'Board mis à jour',
                'id' => $board->id,
                'attributes' => $board
            ], 201);
        } else {
            return response()->json([
                "Board non trouvée"
            ], 404);
        }
    }
}

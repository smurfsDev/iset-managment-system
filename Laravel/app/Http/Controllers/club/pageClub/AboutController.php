<?php

namespace App\Http\Controllers\club\pageClub;

use App\Http\Controllers\Controller;
use App\Models\about;
use Illuminate\Http\Request;
use App\Http\Requests\AboutRequest;

class AboutController extends Controller
{
    public function getAbout($id){
        $about = about::where('idClub','=',$id)->orderBy('updated_at')->paginate(5);
        if (sizeof($about) > 0)
            return response()->json(
                $about,
                200
            );
        else
            return response()->json([
                "Aucun about"
            ], 404);
    }

    public function getClubAbout(Request $request){
        $about = $request->user()->club()->abouts()->get();
        if (sizeof($about) > 0)
            return response()->json(
                $about,
                200
            );
        else
            return response()->json([
                "Aucun about"
            ], 404);
    }

    public function createAbout($id, AboutRequest $request){
        $longDescription = $request->input('longDescription');
        $idClub = $id;

        $newAbout = array(
            "longDescription" => $longDescription,
            "idClub" => $idClub
        );

        $createNewAbout = about::create($newAbout);

        if ($createNewAbout) {
            return response()->json([
                'data' => [
                    'message' => 'About created',
                    'id' => $createNewAbout->id,
                    'attributes' => $createNewAbout
                ]
            ], 201);
        }
        return "About created";
    }
    public function deleteAbout($idClub,$idAbout){

        $about = about::find($idAbout);

        if ($about){
            $about->delete();
            return response()->json([
                'type' => 'About',
                'message' => 'About Supprimé! '
            ], 204);
        }
        else {
            return response()->json([
                'type' => 'Header',
                'message' => 'Header non trouvée'
            ], 404);
        }
    }

    public function updateAbout($idClub, $idAbout, AboutRequest $request){

        $about = about::find($idAbout);
        if ($about) {

            $about->longDescription = $request->input('longDescription') ? $request->input('longDescription') : $about->longDescription;
            $about->idClub = $idClub;
            $about->save();
            return response()->json([
                'message' => 'About mis à jour',
                'id' => $about->id,
                'attributes' => $about
            ], 201);
        } else {
            return response()->json([
                "About non trouvée"
            ], 404);
        }
    }

}

<?php

namespace App\Http\Controllers\club\pageClub;

use App\Http\Controllers\Controller;
use App\Models\about;
use Illuminate\Http\Request;
use App\Http\Requests\AboutRequest;

class AboutController extends Controller
{

    public function show($id){
        $abouts = about::where('idClub', $id)->get();
        if(empty($abouts)){
            return response()->json(['message' => 'Aucun about pour ce club'], 404);
        }
        return response()->json($abouts, 200);
    }

    public function getAbout(Request $request){
        $club = $request->user()->club()->get('id');
        $about = about::where('idClub','=',$club[0]->id)->orderBy('updated_at')->paginate(5);
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

    public function createAbout(AboutRequest $request){
        $longDescription = $request->input('longDescription');
        $club = $request->user()->club()->get('id');
        $idClub = $club[0]->id;

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
    public function deleteAbout($idAbout){
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
                'type' => 'About',
                'message' => 'About non trouvée'
            ], 404);
        }
    }

    public function updateAbout($idAbout, AboutRequest $request){
        $about = about::find($idAbout);
        if ($about) {
            $about->longDescription = $request->input('longDescription') ? $request->input('longDescription') : $about->longDescription;
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

<?php

namespace App\Http\Controllers;

use App\Http\Requests\DemandeAdhesionClubRequest;
use App\Models\club;
use App\Models\DemandeAdhesionClub;
use Illuminate\Http\Request;

class ClubController extends Controller
{

    public function show(){
        $Clubs = club::paginate(5);
        if (empty($Clubs)) {
            return response()->json(['message' => 'No clubs found'], 404);
        }else{
            return response()->json($Clubs, 200);
        }
    }

    public function DemandeeAdhesion(DemandeAdhesionClubRequest $request){
        $user_id = $request->user_id;
        $club_id = $request->input('club_id');
        $message = $request->input('message');
        $status = 0;
        $demande = new DemandeAdhesionClub();
        $demande->user_id = $user_id;
        $demande->club_id = $club_id;
        $demande->message = $message;
        $demande->status = $status;
        $demande->save();
        return response()->json(['success'=>'Demande envoyée avec succès']);
    }

    public function getDemandeAdhesion(Request $request){
        $demandes = $request->user()->DemandeAdhesionClub()->with('club')->paginate(5);
        if (empty($demandes)) {
            return response()->json(['message' => 'No demande found'], 404);
        }else{
            return response()->json($demandes, 200);
        }
    }

    public function deleteDemandeAdhesion(Request $request){
        $demande = $request->user()->DemandeAdhesionClub()->find($request->id);
        if (empty($demande)) {
            return response()->json(['message' => 'No demande found'], 404);
        }else{
            $demande->delete();
            return response()->json(['success'=>'Demande supprimée avec succès']);
        }
    }

    public function getDemandeAdhesionByClub(Request $request){
        $demandes = $request->user()->club()->with('DemandeAdhesionClub')->with('DemandeAdhesionClub.user')->paginate(5);
        return $demandes;
        if (empty($demandes)) {
            return response()->json(['message' => 'No demande found'], 404);
        }else{
            return response()->json($demandes, 200);
        }
    }

    public function acceptDemandeAdhesion(Request $request,$id){
        $demande = DemandeAdhesionClub::find($id);
        if (empty($demande)) {
            return response()->json(['message' => 'No demande found'], 404);
        }else{
            $demande->status = 1;
            $demande->admin_id = $request->user()->id;
            $demande->save();
            return response()->json(['success'=>'Demande acceptée avec succès']);
        }
    }

}

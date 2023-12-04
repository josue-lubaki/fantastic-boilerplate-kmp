
import Foundation
import shared

class DIModule {
    static var koin = {
        KoinInit().doInit(
                appDeclaration: { _ in
                    // Do nothing
                }
        )
    }()
}